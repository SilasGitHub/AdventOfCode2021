import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution61 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 6/input.txt"));
        List<Integer> blowfishes = new ArrayList<>();
        String line = sc.nextLine();
        String[] splitLine = line.split(",");
        for (String string : splitLine) {
            blowfishes.add(Integer.parseInt(string));
        }
        for (int i = 0; i < 80; i++) {
            System.out.println(i);
            int numOfBirths = 0;
            for (int j = 0; j < blowfishes.size(); j++) {
                blowfishes.set(j, blowfishes.get(j) - 1);
                if (blowfishes.get(j) < 0) {
                    blowfishes.set(j, 6);
                    numOfBirths++;
                }
            }
            for (int j = 0; j < numOfBirths; j++) {
                blowfishes.add(8);
            }
        }
        System.out.println(blowfishes.size());
    }
}
