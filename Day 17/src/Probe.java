import java.util.Objects;

public class Probe {
    private int posX = 0;
    private int posY = 0;
    private int initialSpeedX;
    private int initialSpeedY;
    private int speedX;
    private int speedY;

    public Probe(int x, int y) {
        speedX = x;
        speedY = y;
        initialSpeedX = x;
        initialSpeedY = y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void nextStep() {
        posX += speedX;
        posY += speedY;
        if (speedX != 0) {
            speedX += speedX > 0 ? -1 : 1;
        }
        speedY += -1;
    }

    public int getInitialSpeedY() {
        return initialSpeedY;
    }

    public int getInitialSpeedX() {
        return initialSpeedX;
    }

    public int getHighestY() {
        int result = 0;
        int speedYCopy = initialSpeedY;
        while (speedYCopy > 0) {
            result += speedYCopy;
            speedYCopy--;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probe probe = (Probe) o;
        return initialSpeedX == probe.initialSpeedX && initialSpeedY == probe.initialSpeedY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(initialSpeedX, initialSpeedY);
    }
}
