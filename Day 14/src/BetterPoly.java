import java.util.*;

public class BetterPoly {
    Character firstChar;
    Character lastChar;
    Map<String, Long> currentPoly = new HashMap<>();
    Map<String, Character> rules = new HashMap<>();

    public BetterPoly(List<String> input) {
        firstChar = input.get(0).charAt(0);
        lastChar = input.get(0).charAt(input.get(0).length() - 1);
        for (int i = 0; i < input.get(0).length() - 1; i++) {
            String combi = input.get(0).substring(i, i + 2);
            currentPoly.put(combi, currentPoly.getOrDefault(combi, 0L) + 1);
        }
        for (int i = 2; i < input.size(); i++) {
            String[] splitLine = input.get(i).split(" -> ");
            rules.put(splitLine[0], splitLine[1].charAt(0));
        }
    }

    public void nextStep() {
        Map<String, Long> newPol = new HashMap<>();
        for (Map.Entry<String, Long> entry : currentPoly.entrySet()) {
            Character inbetween = rules.get(entry.getKey());
            if (!Objects.isNull(inbetween)) {
                StringBuilder first = new StringBuilder();
                StringBuilder second = new StringBuilder();
                first.append(entry.getKey().charAt(0));
                first.append(inbetween);
                second.append(inbetween);
                second.append(entry.getKey().charAt(1));
                newPol.put(first.toString(), newPol.getOrDefault(first.toString(), 0L) + entry.getValue());
                newPol.put(second.toString(), newPol.getOrDefault(second.toString(), 0L) + entry.getValue());
            } else {
                newPol.put(entry.getKey(), newPol.getOrDefault(entry.getKey(), 0L) + entry.getValue());
            }
        }
        currentPoly = newPol;
    }

    public long numOfMostFrequentChar() {
        long mostFrequent = 0;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            long frequency = 0;
            for (Map.Entry<String, Long> entry : currentPoly.entrySet()) {
                if (entry.getKey().charAt(0) == ch && entry.getKey().charAt(1) == ch) {
                    frequency += 2L * entry.getValue();
                }else if (entry.getKey().charAt(0) == ch || entry.getKey().charAt(1) == ch) {
                    frequency += entry.getValue();
                }
            }
            if (ch == firstChar) {
                frequency++;
            }
            if (ch == lastChar) {
                frequency++;
            }
            frequency = frequency/2;
            if (frequency > mostFrequent) {
                mostFrequent = frequency;
            }
        }
        return mostFrequent;
    }

    public long numOfLeastFrequentChar() {
        long leastFrequent = Long.MAX_VALUE;
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            long frequency = 0;
            for (Map.Entry<String, Long> entry : currentPoly.entrySet()) {
                if (entry.getKey().charAt(0) == ch && entry.getKey().charAt(1) == ch) {
                    frequency += 2L * entry.getValue();
                }else if (entry.getKey().charAt(0) == ch || entry.getKey().charAt(1) == ch) {
                    frequency += entry.getValue();
                }
            }
            if (ch == firstChar) {
                frequency++;
            }
            if (ch == lastChar) {
                frequency++;
            }
            frequency = frequency/2;
            if (frequency < leastFrequent && frequency != 0) {
                leastFrequent = frequency;
            }
        }
        return leastFrequent;
    }
}
