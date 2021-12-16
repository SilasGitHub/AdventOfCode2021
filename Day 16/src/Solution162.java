import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution162 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 16/input.txt"));
        Packet bigPack = new Packet(sc.nextLine(), false);
        System.out.println(bigPack.getValue());
    }
}
