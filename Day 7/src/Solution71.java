import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution71 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 7/input.txt"));
        List<Integer> horPos = new ArrayList<>();
        for (String string : sc.nextLine().split(",")) {
            horPos.add(Integer.parseInt(string));
        }
        int minPos = Collections.min(horPos);
        int maxPos = Collections.max(horPos);
        int leastFuel = Integer.MAX_VALUE;
        for (int i = minPos; i <= maxPos; i++) {
            int fuel = 0;
            for (Integer pos : horPos) {
                fuel += Math.abs(i - pos);
            }
            if (fuel <= leastFuel) {
                leastFuel = fuel;
            }
        }
        System.out.println(leastFuel);
    }
}
