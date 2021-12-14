import java.util.*;

public class Polymerization {
    List<Character> currentPol = new ArrayList<>();
    Map<String, Character> rules = new HashMap<>();

    public Polymerization(List<String> input) {
        for (char c : input.get(0).toCharArray()) {
            currentPol.add(c);
        }
        for (int i = 2; i < input.size(); i++) {
            String[] splitLine = input.get(i).split(" -> ");
            rules.put(splitLine[0], splitLine[1].charAt(0));
        }
    }

    public void nextStep() {
        List<Character> newPol = new ArrayList<>();
        for (int i = 0; i < currentPol.size() - 1; i++) {
            newPol.add(currentPol.get(i));
            StringBuilder sb = new StringBuilder();
            sb.append(currentPol.get(i));
            sb.append(currentPol.get(i + 1));
            Character inbetween = rules.get(sb.toString());
            if (!Objects.isNull(inbetween)) {
                newPol.add(inbetween);
            }
        }
        newPol.add(currentPol.get(currentPol.size() - 1));
        currentPol = newPol;
    }

    public long numOfMostFrequentChar() {
        Set<Character> allChars = new HashSet<>(currentPol);
        long mostFrequent = 0;
        for (Character c : allChars) {
            long frequency = Collections.frequency(currentPol, c);
            if (frequency > mostFrequent) {
                mostFrequent = frequency;
            }
        }
        return mostFrequent;
    }

    public long numOfLeastFrequentChar() {
        Set<Character> allChars = new HashSet<>(currentPol);
        long leastFrequent = Long.MAX_VALUE;
        for (Character c : allChars) {
            long frequency = Collections.frequency(currentPol, c);
            if (frequency < leastFrequent) {
                leastFrequent = frequency;
            }
        }
        return leastFrequent;
    }
}
