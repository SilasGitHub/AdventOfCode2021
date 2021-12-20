import java.util.*;

public class TransparantPaper {
    private Set<Position> currentVals = new HashSet<>();
    private List<Fold> allFolds = new ArrayList<>();
    int currentFold = 0;

    public TransparantPaper(List<String> input) {
        int i;
        for (i = 0; !input.get(i).isBlank(); i++) {
            String[] coordinates = input.get(i).split(",");
            currentVals.add(new Position(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        }
        i++;
        while (i < input.size()) {
            allFolds.add(new Fold(input.get(i)));
            i++;
        }
    }

    public boolean foldToGo() {
        return currentFold < allFolds.size();
    }

    public void applyFold() {
        Fold fold = allFolds.get(currentFold);
        Set<Position> positions = new HashSet<>(currentVals);
        for (Position pos : positions) {
            if (fold.isHorizontal() && pos.getX() > fold.getLineToFold()) {
                currentVals.remove(pos);
                pos.setX(fold.getLineToFold() - Math.abs(fold.getLineToFold() - pos.getX()));
                currentVals.add(pos);
            } else if (!fold.isHorizontal() && pos.getY() > fold.getLineToFold()) {
                currentVals.remove(pos);
                pos.setY(fold.getLineToFold() - Math.abs(fold.getLineToFold() - pos.getY()));
                currentVals.add(pos);
            }
        }
        currentFold++;
    }

    public int getNumberOfDots() {
        return currentVals.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<Position> list = new ArrayList<>(currentVals);
        Collections.sort(list);
        int maxX = 0;
        int maxY = 0;
        for (Position pos : list) {
            if (pos.getX() > maxX) {
                maxX = pos.getX();
            }
            if (pos.getY() > maxY) {
                maxY = pos.getY();
            }
        }
        int y = 0;
        int i = 0;
        while (y <= maxY) {
            for (int x = 0; x <= maxX; x++) {
                if (list.get(i).getX() == x && list.get(i).getY() == y) {
                    result.append("#");
                    if (i < list.size() - 1) {
                        i++;
                    }
                } else {
                    result.append(".");
                }
            }
            y++;
            result.append("\n");
        }
        return result.toString();
    }
}
