import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution182 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 18/input.txt"));
        Deque<SnailNumber> elements = new ArrayDeque<>();
        List<SnailNumber> allSnails = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (ch == ']') {
                    SnailNumber second = elements.removeLast();
                    SnailNumber first = elements.removeLast();
                    elements.add(new SnailNumber(first, second));
                } else if (ch != '[' && ch != ',') {
                    elements.add(new SnailNumber(Integer.parseInt(String.valueOf(ch))));
                }
            }
            allSnails.add(elements.pop());
        }
        long highestMagnitude = 0;
        for (int i = 0; i < allSnails.size(); i++) {
            for (int j = 0; j < allSnails.size(); j++) {
                if (j != i) {
                    SnailNumber result = new SnailNumber(allSnails.get(i), allSnails.get(j));
                    result.nextStep();
                    if (result.getMagnitude() > highestMagnitude) {
                        highestMagnitude = result.getMagnitude();
                    }
                }
            }
        }
        System.out.println(highestMagnitude);
    }
}
