import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution81 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 8/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        List<String> output = new ArrayList<>();
        for (String string : input) {
            output.add(string.split(" \\| ")[1]);
        }
        List<String> splitOutput = new ArrayList<>();
        for (String string : output) {
            splitOutput.addAll(Arrays.asList(string.split(" ")));
        }
        int counter = 0;
        for (String string : splitOutput) {
            if (string.length() == 7 || string.length() == 4 || string.length() == 3 || string.length() == 2) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
