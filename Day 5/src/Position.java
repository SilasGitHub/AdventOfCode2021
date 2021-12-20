import java.util.Objects;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position p = (Position) o;
        return x == p.getX() && y == p.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Position o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
        if (this.equals(o)) {
            return 0;
        }
        Position other = (Position) o;
        if (getY() < other.getY() || (getY() == other.getY() && getX() < other.getX())) {
            return -1;
        } else {
            return 1;
        }
    }
}

