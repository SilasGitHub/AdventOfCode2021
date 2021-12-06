import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution62 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("/Users/degra/Documents/AoC/2021/Day 6/input.txt"));
        long[] blowfishes = new long[9];
        String line = sc.nextLine();
        String[] splitLine = line.split(",");
        for (String string : splitLine) {
            switch (Integer.parseInt(string)) {
                case 1: {
                    blowfishes[1] = blowfishes[1] + 1;
                    break;
                }
                case 2: {
                    blowfishes[2] = blowfishes[2] + 1;
                    break;
                }
                case 3: {
                    blowfishes[3] = blowfishes[3] + 1;
                    break;
                }
                case 4: {
                    blowfishes[4] = blowfishes[4] + 1;
                    break;
                }
                case 5: {
                    blowfishes[5] = blowfishes[5] + 1;
                    break;
                }
                case 6: {
                    blowfishes[6] = blowfishes[6] + 1;
                }
            }
        }
        for (int i = 0; i <= 256; i++) {
            long[] temp = blowfishes.clone();
            System.arraycopy(temp, 1, blowfishes, 0, blowfishes.length - 1);
            blowfishes[6] = temp[7] + temp[0];
            blowfishes[8] = temp[0];
        }
        long total = 0;
        for (int j = 0; j < blowfishes.length - 1; j++) {
            total += blowfishes[j];
        }
        System.out.println(total);
    }
}
