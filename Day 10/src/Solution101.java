import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

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
            Stack<Character> next = new Stack<>();
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
