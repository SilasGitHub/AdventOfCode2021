import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution111 {
    public static final int NUM_OF_TURNS = 100;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 11/input.txt"));
        List<String> input = new ArrayList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        OctoMap om = new OctoMap(input);
        for (int i = 0; i < NUM_OF_TURNS; i++) {
            om.nextStep();
        }
        System.out.println(om.getNumOfFlashes());
    }
}
