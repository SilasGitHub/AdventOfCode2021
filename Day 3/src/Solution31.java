import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution31 {
    public static void main(String[] args) {
        File input = new File("C:\\Users\\degra\\Documents\\AoC\\2021\\Day 3\\input.txt");
        try (Scanner sc = new Scanner(input)) {
            List<String> generator = new ArrayList<>();
            List<String> scrubber = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                generator.add(line);
                scrubber.add(line);
            }
            for (int i = 0; generator.size() != 1 || scrubber.size() != 1; i++){
                if (generator.size() != 1) {
                    int numOfOnes = 0;
                    int total = 0;
                    for (String s : generator) {
                        if (s.charAt(i) == '1') {
                            numOfOnes++;
                            total++;
                        } else {
                            total++;
                        }
                    }
                    boolean moreOnes = (total - numOfOnes) <= numOfOnes;
                    int finalI = i;
                    generator.removeIf(n -> ((n.charAt(finalI) == '1' && !moreOnes) || n.charAt(finalI) == '0' && moreOnes));
                }
                if (scrubber.size() != 1) {
                    int numOfOnes = 0;
                    int total = 0;
                    for (String s : scrubber) {
                        if (s.charAt(i) == '1') {
                            numOfOnes++;
                            total++;
                        } else {
                            total++;
                        }
                    }
                    boolean moreOnes = (total - numOfOnes) <= numOfOnes;
                    int finalI = i;
                    scrubber.removeIf(n -> ((n.charAt(finalI) == '1' && moreOnes) || n.charAt(finalI) == '0' && !moreOnes));
                }
            }
            long generatorValue = Long.parseLong(generator.get(0), 2);
            long scrubberValue = Long.parseLong(scrubber.get(0), 2);
            System.out.println(generatorValue * scrubberValue);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
