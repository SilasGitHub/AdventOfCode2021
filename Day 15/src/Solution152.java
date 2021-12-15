import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution152 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 15/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        Maze maze = new Maze(input);
        System.out.println("enlarging");
        maze.enlarge();
        System.out.println("enlarging done");
        while (true) {
            if (maze.nextStep()) {
                break;
            }
        }
        System.out.println("paths calculated, least risk: ");
        System.out.println(maze.lowestRisk());
    }
}
