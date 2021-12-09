import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CaveMap {
    int maxX;
    int maxY;
    List<Integer> heightMap = new ArrayList<>();

    public CaveMap(List<String> input) {
        maxX = input.get(0).length() - 1;
        maxY = input.size() - 1;
        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                heightMap.add(Integer.parseInt(row.substring(j, j+1)));
            }
        }
    }

    public int getValue(int x, int y) {
        return heightMap.get(x + y * (maxX + 1));
    }

    public List<Integer> getValueOfLowPoints() {
        List<Integer> result = new ArrayList<>();
        List<Location> lowPoints = getLowPoints();
        for (Location loc : lowPoints) {
            result.add(getValue(loc.getX(), loc.getY()));
        }
        return result;
    }

    public List<Location> getLowPoints() {
        List<Location> result = new ArrayList<>();
        for (int x = 0; x <= maxX; x++) {
            for (int y = 0; y <= maxY; y++) {
                boolean lowest = true;
                int value = getValue(x, y);
                if ((y != 0 && value >= getValue(x,y - 1)) ||
                        (x != 0 && value >= getValue(x - 1, y)) ||
                        (y != maxY && value >= getValue(x, y + 1)) ||
                        (x != maxX && value >= getValue(x + 1, y)))   {
                    lowest = false;
                }
                if (lowest) {
                    result.add(new Location(x, y));
                }
            }
        }
        return result;
    }

    public List<Location> lowSurrounding(int x, int y) {
        List<Location> result = new ArrayList<>();
        int value = getValue(x, y);
        if (x != 0 && getValue(x - 1, y) != 9 && getValue(x - 1, y) > value) {
            result.add(new Location(x -1, y));
        }
        if (x != maxX && getValue(x + 1, y) != 9 && getValue(x + 1, y) > value) {
            result.add(new Location(x + 1, y));
        }
        if (y != 0 && getValue(x, y - 1) != 9 && getValue(x, y - 1) > value) {
            result.add(new Location(x, y -1));
        }
        if (y != maxY && getValue(x, y + 1) != 9 && getValue(x, y + 1) > value) {
            result.add(new Location(x, y + 1));
        }
        return result;
    }

    public Set<Location> getBassin(int x, int y) {
        Set<Location> result = new HashSet<>();
        result.add(new Location(x, y));
        List<Location> neighbours = lowSurrounding(x, y);
        result.addAll(neighbours);
        List<List<Location>> toBeChecked = new ArrayList<>();
        toBeChecked.add(neighbours);
        for (int i = 0; i < toBeChecked.size(); i++) {
            for (Location loc : toBeChecked.get(i)) {
                List<Location> newNeighbours = lowSurrounding(loc.getX(), loc.getY());
                if (!newNeighbours.isEmpty()) {
                    result.addAll(newNeighbours);
                    toBeChecked.add(newNeighbours);
                }
            }
        }
        return result;
    }
}
