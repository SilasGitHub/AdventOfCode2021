public class Area {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    public Area(int minX, int maxX, int minY, int maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }

    public boolean validVelocity(Probe p) {
        boolean valid = false;
        while (!(p.getPosY() < minY && p.getSpeedY() < 0)) {
            if (p.getPosX() >= minX && p.getPosX() <= maxX && p.getPosY() >= minY && p.getPosY() <= maxY) {
                valid = true;
                break;
            }
            p.nextStep();
        }
        return valid;
    }

    public int getMinimalXSpeed(int largerThan) {
        for (int initialSpeed = largerThan + 1; initialSpeed <= maxX; initialSpeed++) {
            long x = 0;
            int speed = initialSpeed;
            while (x <= maxX && speed >= 0) {
                x += speed;
                speed--;
                if (x >= minX && x <= maxX) {
                    return initialSpeed;
                }
            }
        }
        return Short.MIN_VALUE;
    }

    public int getMaximumYSpeed(int lessThan) {
        for (int initialSpeed = lessThan - 1; initialSpeed >= minY; initialSpeed--) {
            long y = 0;
            int speed = initialSpeed;
            while (!(y < minY && speed <= 0)) {
                y += speed;
                speed--;
                if (y >= minY && y <= maxY) {
                    return initialSpeed;
                }
            }
        }
        return Short.MIN_VALUE;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }
}
