import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution121 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 12/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        CaveSystem cs = new CaveSystem(input, 1);
        System.out.println(cs.numOfRoutes());
    }
}
