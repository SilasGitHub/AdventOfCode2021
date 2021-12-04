import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution41 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/degra/Documents/AoC/2021/Day 4/input.txt");
        Scanner sc = new Scanner(file);
        String firstLine = sc.nextLine();
        String[] splitLine = firstLine.split(",");
        List<Integer> chosenNums = new ArrayList<>();
        for (int i = 0; i < splitLine.length; i++) {
            chosenNums.add(Integer.parseInt(splitLine[i]));
        }

        List<List<Integer>> allBoards = new ArrayList<>();
        while(sc.hasNext()) {
            List<Integer> board = new ArrayList<>();
            sc.nextLine();
            for (int i = 0; i < 5; i++) {
                String line = sc.nextLine();
                if (line.charAt(0) == ' ') {
                    line = line.substring(1);
                }
                splitLine = line.split("  | ");
                for (int j = 0; j < splitLine.length; j++) {
                    board.add(Integer.parseInt(splitLine[j]));
                }
            }
            allBoards.add(board);
        }

        int leastTurns = 100;
        int boardIndex = -1;
        for (int i = 0; i < allBoards.size(); i++) {
            int numOfTurns = howLongToWin(allBoards.get(i), chosenNums);
            if (numOfTurns < leastTurns) {
                leastTurns = numOfTurns;
                boardIndex = i;
            }
        }
        int remaining = countRemaining(allBoards.get(boardIndex));
        System.out.println(remaining * chosenNums.get(leastTurns - 1));
    }

    private static int countRemaining(List<Integer> board) {
        int total = 0;
        for (Integer i : board) {
            if (i != 100) {
                total += i;
            }
        }
        return total;
    }

    private static int howLongToWin(List<Integer> board, List<Integer> chosenNums) {
        int turns = 0;
        for (int i = 0; !isWinner(board); i++) {
            if (board.contains(chosenNums.get(i))) {
                board.set(board.indexOf(chosenNums.get(i)), 100);
            }
            turns++;
        }
        return turns;
    }

    private static boolean isWinner(List<Integer> board){
        for (int i = 0; i < 5; i++) {
            boolean markedRow = true;
            boolean markedColumn = true;
            for(int j = 0; j < 5; j++) {
                if (!board.get(i * 5 + j).equals(100)) {
                    markedRow = false;
                }
                if (!board.get(j * 5 + i).equals(100)) {
                    markedColumn = false;
                }
            }
            if (markedRow || markedColumn) {
                return true;
            }
        }
        return false;
    }
}
