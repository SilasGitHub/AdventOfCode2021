import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution51 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/users/degra/Documents/AoC/2021/Day 5/input.txt"));
        ArrayList<PipeLine> pipes = new ArrayList<>();
        while(sc.hasNext()) {
            pipes.add(new PipeLine(sc.nextLine()));
        }

        HashMap<Position, Integer> numOfPipes = new HashMap<>();
        for (PipeLine pipe : pipes) {
            if (pipe.isHorOrVert()) {
                List<Position> covered = pipe.pipePositions();
                for (Position pos : covered) {
                    numOfPipes.put(pos, numOfPipes.getOrDefault(pos, 0) + 1);
                }
            }
        }

        int maxCovers = 2;
        int maxNumOfPipes = 0;
        for (Integer i : numOfPipes.values()) {
            if (i >= maxCovers) {
                maxNumOfPipes++;
            }
        }
        System.out.println(maxNumOfPipes);
    }
}
