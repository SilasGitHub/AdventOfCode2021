import java.io.*;

public class Solution22 {
    public static void main(String[] args) {
        int depth = 0;
        int forward = 0;
        int aim = 0;
        File input = new File("C:\\Users\\degra\\Documents\\AoC\\2021\\Day 2\\input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arguments = line.split(" ");
                switch (arguments[0]) {
                    case "forward": {
                        forward += Integer.parseInt(arguments[1]);
                        depth += (Integer.parseInt(arguments[1]) * aim);
                        break;
                    }
                    case "up": {
                        aim -= Integer.parseInt(arguments[1]);
                        break;
                    }
                    case "down": {
                        aim += Integer.parseInt(arguments[1]);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(forward * depth);
    }
}
