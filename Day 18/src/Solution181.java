import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution181 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 18/input.txt"));
        Deque<SnailNumber> elements = new ArrayDeque<>();
        SnailNumber mainSnail = null;
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
            if (mainSnail == null) {
                mainSnail = elements.pop();
            } else {
                mainSnail = new SnailNumber(mainSnail, elements.pop());
            }
            mainSnail.nextStep();
        }
        System.out.println(mainSnail.getMagnitude());
    }
}
