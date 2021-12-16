import com.sun.jdi.ObjectCollectedException;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<Octopus> currentPath = new ArrayList<>();
    private int totalRisk = 0;
    private final Octopus finalNode;
    private boolean complete = false;

    public boolean isComplete() {
        return complete;
    }

    public Path(Octopus firstNode, Octopus finalNode) {
        currentPath.add(firstNode);
        this.finalNode = finalNode;
    }

    public void setCurrentPath(List<Octopus> path) {
        currentPath = path;
        totalRisk = 0;
        for (Octopus octo : path) {
            totalRisk += octo.getValue();
            if (octo.equals(finalNode)) {
                complete = true;
            }
        }
    }

    public double expectedRisk() {
        return totalRisk + 0.5 *
                Math.abs(currentPath.get(currentPath.size() - 1).getX() - finalNode.getX()) + 0.5 *
                Math.abs(currentPath.get(currentPath.size() - 1).getY() - finalNode.getY());
    }

    public List<Octopus> getCurrentPath() {
        return currentPath;
    }

    public void addToPath(Octopus octo) {
        totalRisk += octo.getValue();
        currentPath.add(octo);
        if (octo.equals(finalNode)) {
            complete = true;
        }
    }
}
