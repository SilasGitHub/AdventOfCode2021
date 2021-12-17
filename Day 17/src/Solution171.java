import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution171 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("Day 17/input.txt"));
        String line = sc.nextLine();
        String[] splitLine = line.split("=|\\.\\.|," );
        Area area = new Area(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5]));
        int speedX = 0;
        Probe probe;
        do {
            speedX = area.getMinimalXSpeed(speedX);
            byte speedY = Byte.MAX_VALUE;
            do {
                speedY = (byte) area.getMaximumYSpeed(speedY);
                probe = new Probe(speedX, speedY);
                if (area.validVelocity(probe) && speedY != -1) {
                    System.out.println(probe.getHighestY());
                    return;
                }
            } while (speedY != -1);
        } while (speedX != -1);
    }
}
