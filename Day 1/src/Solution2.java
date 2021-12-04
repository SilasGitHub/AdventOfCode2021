import java.io.*;

public class Solution2 {
    public static void main(String[] args) {
        int result = 0;
        File input = new File("C:\\Users\\degra\\Documents\\AoC\\2021\\Day 1\\input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int oldSum = 99999;
            int oldHeight1 = 9999;
            int oldHeight2 = 9999;
            while ((line = br.readLine()) != null) {
                int height = Integer.parseInt(line);
                int sum = oldHeight1 + oldHeight2 + height;
                if (sum > oldSum) {
                    result++;
                }
                oldSum = sum;
                oldHeight1 = oldHeight2;
                oldHeight2 = height;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}

