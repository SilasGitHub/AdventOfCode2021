import com.sun.jdi.ObjectCollectedException;

import java.util.*;

public class Maze {
    List<Octopus> positions = new ArrayList<>();
    List<Path> paths = new ArrayList<>();
    int maxX;
    int maxY;
    Octopus finalNode;
    Set<Octopus> octos = new HashSet<>();

    public Maze(List<String> input) {
        maxX = input.get(0).length() - 1;
        maxY = input.size() - 1;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                positions.add(new Octopus(j, i, Integer.parseInt(input.get(i).substring(j, j + 1))));
            }
            if (i == input.size() - 1) {
                finalNode = new Octopus(input.get(i).length() - 1, i, Integer.parseInt(input.get(i).substring(input.get(i).length() - 1)));
            }
        }
        paths.add(new Path(new Octopus(0,0, 0), finalNode));
        octos.add(new Octopus(0, 0, 0));
    }

    public void enlarge() {
        List<Octopus> newPositions = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                System.out.println("x = " + i + ", y = " + j);
                for (int h = 0; h < positions.size(); h++) {
                    Octopus octo = positions.get(h);
                    int newValue = octo.getValue() + i + j;
                    if (newValue > 9) {
                        newValue = newValue % 9;
                    }
                    newPositions.add(new Octopus((octo.getX() + i * (maxX + 1)), (octo.getY() + j * (maxY + 1)), newValue));
                }
            }
        }
        Collections.sort(newPositions);
        positions = newPositions;
        maxX = 5 * (maxX + 1) - 1;
        maxY = 5 * (maxY + 1) - 1;
        finalNode = getOcto(maxX, maxY);
        paths.clear();
        paths.add(new Path(new Octopus(0,0, 0), finalNode));
    }

    public void fit(Octopus octo, List<Octopus> octos) {
        for (int i = 0; i < octos.size(); i++) {
            if ((octo.getX() < octos.get(i).getX() && octo.getY() == octos.get(i).getY()) || (octo.getY() < octos.get(i).getY())) {
                octos.add(i, octo);
                return;
            }
        }
        octos.add(octo);
    }

    public boolean nextStep() {
        boolean done = false;
        int leastRisk = Integer.MAX_VALUE;
        Path toExpand = new Path(new Octopus(0,0,0), finalNode);
        for (Path path : paths) {
            if (path.expectedRisk() < leastRisk) {
                leastRisk = path.expectedRisk();
                toExpand = path;
            }
        }
        paths.remove(toExpand);
        for (Octopus octo : nextNodes(toExpand)) {
            if (!octos.contains(octo)) {
                List<Octopus> currentPath = new ArrayList<>(toExpand.getCurrentPath());
                currentPath.add(octo);
                Path expandedPath = new Path(new Octopus(0, 0, 0), finalNode);
                expandedPath.setCurrentPath(currentPath);
                if (currentPath.get(currentPath.size() - 1).equals(finalNode)) {
                    done = true;
                }
                paths.add(expandedPath);
                octos.add(octo);
            }
        }
        return done;
    }

    public int lowestRisk() {
        int lowest = Integer.MAX_VALUE;
        for (Path path : paths) {
            if (path.isComplete() && path.expectedRisk() < lowest) {
                lowest = path.expectedRisk();
            }
        }
        return lowest;
    }

    public Octopus getOcto(int x, int y) {
        return positions.get(x + y * (maxX + 1));
    }

    public List<Octopus> nextNodes(Path path) {
        List<Octopus> result = new ArrayList<>();
        Octopus current = path.getCurrentPath().get(path.getCurrentPath().size() - 1);
        if (path.getCurrentPath().size() <= 1) {
            result.add(getOcto(current.getX() + 1, current.getY()));
            result.add(getOcto(current.getX(), current.getY() + 1));
            return result;
        }
        Octopus last = path.getCurrentPath().get(path.getCurrentPath().size() - 2);
        if (current.getX() != 0 && !last.equals(getOcto(current.getX() - 1, current.getY()))) {
            result.add(getOcto(current.getX() - 1, current.getY()));
        }
        if (current.getY() != 0 && !last.equals(getOcto(current.getX(), current.getY() - 1))) {
            result.add(getOcto(current.getX(), current.getY() - 1));
        }
        if (current.getX() != maxX && !last.equals(getOcto(current.getX() + 1, current.getY()))) {
            result.add(getOcto(current.getX() + 1, current.getY()));
        }
        if (current.getY() != maxY && !last.equals(getOcto(current.getX(), current.getY() + 1))) {
            result.add(getOcto(current.getX(), current.getY() + 1));
        }
        return result;
    }
}
