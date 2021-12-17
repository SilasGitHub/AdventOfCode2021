import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution172 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner("target area: x=20..30, y=-10..-5");
        String line = sc.nextLine();
        String[] splitLine = line.split("=|\\.\\.|," );
        Area area = new Area(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), Integer.parseInt(splitLine[4]), Integer.parseInt(splitLine[5]));
        short speedX = 0;
        Probe probe;
        Set<Probe> allProbes = new HashSet<>();
        do {
            speedX = (short) area.getMinimalXSpeed(speedX);
            short speedY = Short.MAX_VALUE;
            do {
                speedY = (short) area.getMaximumYSpeed(speedY);
                probe = new Probe(speedX, speedY);
                if (area.validVelocity(probe)) {
                    System.out.println("x = " + probe.getInitialSpeedX() + ", y = " + probe.getInitialSpeedY());
                    allProbes.add(probe);
                }
            } while (speedY != Short.MIN_VALUE);
        } while (speedX != Short.MIN_VALUE);
        System.out.println(allProbes.size());
    }
}
