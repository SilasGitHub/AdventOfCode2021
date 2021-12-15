import java.util.Objects;

public class Octopus {
    private int x;
    private int y;
    private int value;

    public Octopus(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increaseValue() {
        value++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Octopus)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Octopus c = (Octopus) o;

        // Compare the data members and return accordingly
        return this.getX() == c.getX() && this.getY() == c.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
