import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution101 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 10/input.txt"));
        int score = 0;
        Map<Character, Integer> scores = new HashMap<>();
        scores.put(')', 3);
        scores.put(']', 57);
        scores.put('}',1197);
        scores.put('>', 25137);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Deque<Character> next = new ArrayDeque<>();
            for (Character c : line.toCharArray()) {
                if (c == '(') {
                    next.add(')');
                } else if (c == '[') {
                    next.add(']');
                } else if (c == '{') {
                    next.add('}');
                } else if (c == '<'){
                    next.add('>');
                } else if (c != next.pop()) {
                    score += scores.get(c);
                }
            }
        }
        System.out.println(score);
    }
}
