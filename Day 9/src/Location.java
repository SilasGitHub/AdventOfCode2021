import java.util.HashMap;
import java.util.Objects;

public class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Location)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Location c = (Location) o;

        // Compare the data members and return accordingly
        return this.getX() == c.getX() && this.getY() == c.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
