import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution112 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 11/input.txt"));
        List<String> input = new ArrayList<>();
        while(sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        OctoMap om = new OctoMap(input);
        int turns = 0;
        while(!om.haveAllOctosFlashed()) {
            om.nextStep();
            turns++;
        }
        System.out.println(turns);
    }
}
