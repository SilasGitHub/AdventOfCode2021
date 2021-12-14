import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution142 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 14/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        BetterPoly poly = new BetterPoly(input);
        for (int i = 0; i < 40; i++) {
            poly.nextStep();
        }
        System.out.println(poly.numOfMostFrequentChar() - poly.numOfLeastFrequentChar());
    }
}
