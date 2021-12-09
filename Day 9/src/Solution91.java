import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution91 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 9/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        CaveMap cm = new CaveMap(input);
        List<Integer> lowPoints = cm.getValueOfLowPoints();
        int total = 0;
        for (Integer integer: lowPoints) {
            total += integer + 1;
        }
        System.out.println(total);
    }
}
