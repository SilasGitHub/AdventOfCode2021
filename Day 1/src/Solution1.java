import java.io.*;

public class Solution1 {
    public static void main(String[] args) {
        int result = 0;
        File input = new File("C:\\Users\\degra\\Documents\\AoC\\2021\\Day 1\\input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            int oldHeight = 9999;
            while ((line = br.readLine()) != null) {
                int height = Integer.parseInt(line);
                if (height > oldHeight) {
                    result++;
                }
                oldHeight = height;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
