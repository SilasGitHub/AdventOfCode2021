import java.util.*;

public class CaveSystem {
    public static final String START_NODE = "start";
    public static final String END_NODE = "end";

    private final int numOfVisitsToSmallCaves;
    private Map<String, Set<String>> nextNodes = new HashMap<>();

    public CaveSystem(List<String> input, int numOfVisitsToSmallCaves) {
        this.numOfVisitsToSmallCaves = numOfVisitsToSmallCaves;
        String[] route;
        for (String line : input) {
            route = line.split("-");
            if (!route[1].equals(START_NODE) && !route[0].equals(END_NODE)) {
                Set<String> current = nextNodes.getOrDefault(route[0],  new HashSet<>());
                current.add(route[1]);
                nextNodes.put(route[0], current);
            }
            if (!route[0].equals(START_NODE) && !route[1].equals(END_NODE)) {
                Set<String> current = nextNodes.getOrDefault(route[1],  new HashSet<>());
                current.add(route[0]);
                nextNodes.put(route[1], current);
            }
        }
    }

    public Set<Map.Entry<List<String>, Map<String, Integer>>> possibleRoutes(List<String> current, Map<String, Integer> visited, boolean visitedSmall) {
        Set<String> possibleNext = new HashSet<>(nextNodes.get(current.get(current.size() - 1)));
        for (Map.Entry<String, Integer> entry : visited.entrySet()) {
            if (entry.getValue() >= numOfVisitsToSmallCaves || visitedSmall) {
                possibleNext.remove(entry.getKey());
            }
        }
        Set<Map.Entry<List<String>, Map<String, Integer>>> result = new HashSet<>();
        for (String next : possibleNext) {
            List<String> newRoute = new ArrayList<>(current);
            newRoute.add(next);
            Map<String, Integer> newVisited = new HashMap<>(visited);
            if (Character.isLowerCase(next.charAt(0))) {
                newVisited.put(next, newVisited.getOrDefault(next, 0) + 1);
            }
            Map.Entry<List<String>, Map<String, Integer>> entry = new AbstractMap.SimpleEntry<>(newRoute, newVisited);
            result.add(entry);
        }
        return result;
    }

    public int numOfRoutes() {
        return makeRoutes().size();
    }

    public boolean isOverSmallCaveLimit(Map<String, Integer> visited) {
        for (Map.Entry<String, Integer> entry : visited.entrySet()) {
            if (!entry.getKey().equals(START_NODE) && entry.getValue() >= numOfVisitsToSmallCaves) {
                return true;
            }
        }
        return false;
    }

    public Set<List<String>> makeRoutes() {
        Set<List<String>> allRoutes = new HashSet<>();
        Map<List<String>, Map<String, Integer>> inProgress = new HashMap<>();
        List<String> firstRoute = new ArrayList<>();
        firstRoute.add(START_NODE);
        Map<String, Integer> visited = new HashMap<>();
        visited.put(START_NODE, numOfVisitsToSmallCaves);
        inProgress.put(firstRoute, visited);
        while(inProgress.size() != 0) {
            Map<List<String>,Map<String, Integer>> tempMap = new HashMap<>();
            for (Map.Entry<List<String>, Map<String, Integer>> entry : inProgress.entrySet()) {
                for (Map.Entry<List<String>, Map<String, Integer>> newEntry : possibleRoutes(entry.getKey(), entry.getValue(), isOverSmallCaveLimit(entry.getValue()))) {
                    if (newEntry.getKey().get(newEntry.getKey().size() - 1).equals(END_NODE)) {
                        allRoutes.add(newEntry.getKey()) ;
                    } else {
                        tempMap.put(newEntry.getKey(), newEntry.getValue());
                    }
                }
            }
            inProgress = tempMap;
        }
        return allRoutes;
    }
}
