import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution82 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 8/input.txt"));
        List<WireSegmentCombination> combis = new ArrayList<>();
        while (sc.hasNextLine()) {
            combis.add(new WireSegmentCombination(sc.nextLine()));
            combis.get(combis.size() - 1).makeMap();
        }
        int total = 0;
        for (WireSegmentCombination w : combis) {
            total += w.getScore();
        }
        System.out.println(total);
    }

}
