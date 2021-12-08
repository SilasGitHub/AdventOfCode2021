import java.util.*;

public class WireSegmentCombination {
    String[] input;
    String[] output;
    Map<Character, List<Character>> wireToSegment = new HashMap<>();

    public static final String ZERO = "abcefg";
    public static final String ONE = "cf";
    public static final String TWO = "acdeg";
    public static final String THREE = "acdfg";
    public static final String FOUR = "bcdf";
    public static final String FIVE = "abdfg";
    public static final String SIX = "abdefg";
    public static final String SEVEN = "acf";
    public static final String EIGHT = "abcdefg";
    public static final String NINE = "abcdfg";

    public WireSegmentCombination(String data) {
        String[] splitData = data.split(" \\| ");
        input = splitData[0].split(" ");
        output = splitData[1].split(" ");
    }

    public void makeMap() {
        for (String string : input) {
            switch (string.length()) {
                case 2: {
                    for (char c : string.toCharArray()) {
                        List<Character> newList = new ArrayList<>();
                        if (wireToSegment.containsKey(c)) {
                            List<Character> currentList = wireToSegment.get(c);
                            if (currentList.contains('c')) {
                                newList.add('c');
                            }
                            if (currentList.contains('f')) {
                                newList.add('f');
                            }
                        } else {
                            newList.add('c');
                            newList.add('f');
                        }
                        wireToSegment.put(c, newList);
                    }
                    break;
                }
                case 3: {
                    for (char c : string.toCharArray()) {
                        List<Character> newList = new ArrayList<>();
                        if (wireToSegment.containsKey(c)) {
                            List<Character> currentList = wireToSegment.get(c);
                            if (currentList.contains('a')) {
                                newList.add('a');
                            }
                            if (currentList.contains('c')) {
                                newList.add('c');
                            }
                            if (currentList.contains('f')) {
                                newList.add('f');
                            }
                        } else {
                            newList.add('a');
                            newList.add('c');
                            newList.add('f');
                        }
                        wireToSegment.put(c, newList);
                    }
                    break;
                }
                case 4: {
                    for (char c : string.toCharArray()) {
                        List<Character> newList = new ArrayList<>();
                        if (wireToSegment.containsKey(c)) {
                            List<Character> currentList = wireToSegment.get(c);
                            if (currentList.contains('b')) {
                                newList.add('b');
                            }
                            if (currentList.contains('c')) {
                                newList.add('c');
                            }
                            if (currentList.contains('d')) {
                                newList.add('d');
                            }
                            if (currentList.contains('f')) {
                                newList.add('f');
                            }
                        } else {
                            newList.add('b');
                            newList.add('c');
                            newList.add('d');
                            newList.add('f');
                        }
                        wireToSegment.put(c, newList);
                    }
                    break;
                }
                case 7: {
                    for (char c : string.toCharArray()) {
                        List<Character> newList = new ArrayList<>();
                        if (wireToSegment.containsKey(c)) {
                            List<Character> currentList = wireToSegment.get(c);
                            if (currentList.contains('a')) {
                                newList.add('a');
                            }
                            if (currentList.contains('b')) {
                                newList.add('b');
                            }
                            if (currentList.contains('c')) {
                                newList.add('c');
                            }
                            if (currentList.contains('d')) {
                                newList.add('d');
                            }
                            if (currentList.contains('e')) {
                                newList.add('e');
                            }
                            if (currentList.contains('f')) {
                                newList.add('f');
                            }
                            if (currentList.contains('g')) {
                                newList.add('g');
                            }
                        } else {
                            newList.add('a');
                            newList.add('b');
                            newList.add('c');
                            newList.add('d');
                            newList.add('e');
                            newList.add('f');
                            newList.add('g');
                        }
                        wireToSegment.put(c, newList);
                    }
                    break;
                }
            }
        }
        for (Map.Entry<Character, List<Character>> entry : wireToSegment.entrySet()) {
            if (entry.getValue().size() == 3) {
                entry.getValue().clear();
                entry.getValue().add('a');
            } else if (entry.getValue().size() == 4) {
                entry.getValue().clear();
                entry.getValue().add('b');
                entry.getValue().add('d');
            } else if (entry.getValue().size() == 7) {
                entry.getValue().clear();
                entry.getValue().add('e');
                entry.getValue().add('g');
            }
        }
        for (Map.Entry<Character, List<Character>> entry : wireToSegment.entrySet()) {
            int counter = 0;
            for (String string : input) {
                if (string.contains(entry.getKey().toString())) {
                    counter++;
                }
            }
            if (counter == 6) {
                entry.getValue().clear();
                entry.getValue().add('b');
            } else if (counter == 8 && entry.getValue().size() != 1) {
                entry.getValue().clear();
                entry.getValue().add('c');
            } else if (counter == 7 && entry.getValue().contains('d')) {
                entry.getValue().clear();
                entry.getValue().add('d');
            } else if (counter == 4) {
                entry.getValue().clear();
                entry.getValue().add('e');
            } else if (counter == 9) {
                entry.getValue().clear();
                entry.getValue().add('f');
            } else if (counter == 7) {
                entry.getValue().clear();
                entry.getValue().add('g');
            }
        }
    }
    //  a = 8
    //  b = 6
    //  c = 8
    //  d = 7
    //  e = 4
    //  f = 9
    //  g = 7

    public int getScore() {
        List<Character> result = new ArrayList<>();
        for (String string : output) {
            List<Character> listOfLetter = new ArrayList<>();
            for (char c : string.toCharArray()) {
                listOfLetter.add(wireToSegment.get(c).get(0));
            }
            Collections.sort(listOfLetter);
            String letter = listOfLetter.toString()
                    .substring(1, 3 * listOfLetter.size() - 1)
                    .replaceAll(", ", "");
            char number = 'a';
            if (letter.equals(ZERO)) {
                number = '0';
            } else if (letter.equals(ONE)) {
                number = '1';
            } else if (letter.equals(TWO)) {
                number = '2';
            } else if (letter.equals(THREE)) {
                number = '3';
            } else if (letter.equals(FOUR)) {
                number = '4';
            } else if (letter.equals(FIVE)) {
                number = '5';
            } else if (letter.equals(SIX)) {
                number = '6';
            } else if (letter.equals(SEVEN)) {
                number = '7';
            } else if (letter.equals(EIGHT)) {
                number = '8';
            } else if (letter.equals(NINE)) {
                number = '9';
            }
            result.add(number);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : result) {
            sb.append(c);
        }
        return Integer.parseInt(sb.toString());

    }

    public Map<Character, List<Character>> getMap() {
        return wireToSegment;
    }
}
