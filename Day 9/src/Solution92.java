import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution92 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 9/input.txt"));
        List<String> input = new ArrayList<>();
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        CaveMap cm = new CaveMap(input);
        List<Location> lowPoints = cm.getLowPoints();
        List<Integer> sizes = new ArrayList<>();
        for (Location loc : lowPoints) {
            Set<Location> bassin = cm.getBassin(loc.getX(), loc.getY());
            sizes.add(bassin.size());
        }
        Collections.sort(sizes);
        System.out.println(sizes.get(sizes.size() - 1) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 3));
    }
}
