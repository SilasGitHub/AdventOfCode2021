import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution141 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 14/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        Polymerization poly = new Polymerization(input);
        for (int i = 0; i < 10; i++) {
            poly.nextStep();
        }
        System.out.println(poly.numOfMostFrequentChar() - poly.numOfLeastFrequentChar());
    }
}
