import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution102 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 10/input.txt"));
        Map<Character, Integer> mappedScores = new HashMap<>();
        mappedScores.put(')', 1);
        mappedScores.put(']', 2);
        mappedScores.put('}', 3);
        mappedScores.put('>', 4);
        List<Long> scores = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Deque<Character> next = new ArrayDeque<>();
            boolean corrupted = false;
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
                    corrupted = true;
                    break;
                }
            }
            if (next.size() != 0 && !corrupted) {
                long score = 0;
                while (!next.isEmpty()) {
                    score *= 5;
                    score += mappedScores.get(next.pop());
                }
                scores.add(score);
            }
        }
        Collections.sort(scores);
        System.out.println(scores.get(scores.size()/2));
    }
}
