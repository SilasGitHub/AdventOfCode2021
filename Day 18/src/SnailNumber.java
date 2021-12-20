import java.util.ArrayList;
import java.util.List;

public class SnailNumber {
    private SnailNumber firstElement;
    private SnailNumber secondElement;
    private boolean isRegular;
    private Integer value;

    public SnailNumber(Integer value) {
        isRegular = true;
        this.value = value;
    }

    public SnailNumber(SnailNumber firstElement, SnailNumber secondElement) {
        isRegular = false;
        if (firstElement.isRegular) {
            this.firstElement = new SnailNumber(firstElement.getValue());
        } else {
            this.firstElement = new SnailNumber(firstElement.getFirstElement(), firstElement.getSecondElement());
        }
        if (secondElement.isRegular) {
            this.secondElement = new SnailNumber(secondElement.getValue());
        } else {
            this.secondElement = new SnailNumber(secondElement.getFirstElement(), secondElement.getSecondElement());
        }
    }

    public SnailNumber getByIndex(int index) {
        if (index == 0) {
            return firstElement;
        } else if (index == 1) {
            return secondElement;
        }
        return null;
    }

    public boolean isRegular() {
        return isRegular;
    }

    public Integer getValue() {
        return value;
    }

    public SnailNumber getFirstElement() {
        return firstElement;
    }

    public SnailNumber getSecondElement() {
        return secondElement;
    }

    public ArrayList<SnailNumber> getValues() {
        ArrayList<SnailNumber> result = new ArrayList<>();
        if (firstElement.isRegular) {
            result.add(firstElement);
        } else {
            result.addAll(firstElement.getValues());
        }
        if (secondElement.isRegular) {
            result.add(secondElement);
        } else {
            result.addAll(secondElement.getValues());
        }
        return result;
    }

    public SnailNumber shouldSplit() {
        List<SnailNumber> allValues = getValues();
        for (int i = 0; i < allValues.size(); i++) {
            if (allValues.get(i).getValue() >= 10) {
                return allValues.get(i);
            }
        }
        return null;
    }

    List<List<Integer>> getAllPaths() {
        List<List<Integer>> result = new ArrayList<>();
        buildPaths(result, firstElement, true);
        buildPaths(result, secondElement, false);
        return result;
    }

    private void buildPaths(List<List<Integer>> result, SnailNumber element, boolean isFirst) {
        if (element.isRegular) {
            List<Integer> resultElement = new ArrayList<>();
            resultElement.add(isFirst ? 0 : 1);
            result.add(resultElement);
        } else {
            for (List<Integer> elementList : element.getAllPaths()) {
                List<Integer> resultElement = new ArrayList<>();
                resultElement.add(isFirst ? 0 : 1);
                resultElement.addAll(elementList);
                result.add(resultElement);
            }
        }
    }

    public void nextStep() {
        List<List<Integer>> allPaths;
        List<Integer> explodeIndexes;
        SnailNumber snailToSplit;
        do {
            allPaths = getAllPaths();
            explodeIndexes = shouldExplode(allPaths);
            snailToSplit = shouldSplit();
            if (explodeIndexes != null) {
                SnailNumber toExplode = getByIndex(explodeIndexes.get(0)).
                        getByIndex(explodeIndexes.get(1)).
                        getByIndex(explodeIndexes.get(2)).
                        getByIndex(explodeIndexes.get(3));
                if (explodeIndexes.get(explodeIndexes.size() - 1) != 1) {
                    List<Integer> toLeftOfExplode = allPaths.get(explodeIndexes.get(explodeIndexes.size() - 1) - 2);
                    SnailNumber leftOfExplode = getByIndex(toLeftOfExplode.get(0));
                    if (toLeftOfExplode.size() >= 2) {
                        leftOfExplode = leftOfExplode.getByIndex(toLeftOfExplode.get(1));
                    }
                    if (toLeftOfExplode.size() >= 3) {
                        leftOfExplode = leftOfExplode.getByIndex(toLeftOfExplode.get(2));
                    }
                    if (toLeftOfExplode.size() >= 4) {
                        leftOfExplode = leftOfExplode.getByIndex(toLeftOfExplode.get(3));
                    }
                    if (toLeftOfExplode.size() == 5) {
                        leftOfExplode = leftOfExplode.getByIndex(toLeftOfExplode.get(4));
                    }
                    leftOfExplode.setValue(leftOfExplode.getValue() + toExplode.getFirstElement().getValue());
                }
                if (explodeIndexes.get(explodeIndexes.size() - 1) != allPaths.size() - 1) {
                    List<Integer> toRightOfExplode = allPaths.get(explodeIndexes.get(explodeIndexes.size() - 1) + 1);
                    SnailNumber rightOfExplode = getByIndex(toRightOfExplode.get(0));
                    if (toRightOfExplode.size() >= 2) {
                        rightOfExplode = rightOfExplode.getByIndex(toRightOfExplode.get(1));
                    }
                    if (toRightOfExplode.size() >= 3) {
                        rightOfExplode = rightOfExplode.getByIndex(toRightOfExplode.get(2));
                    }
                    if (toRightOfExplode.size() >= 4) {
                        rightOfExplode = rightOfExplode.getByIndex(toRightOfExplode.get(3));
                    }
                    if (toRightOfExplode.size() == 5) {
                        rightOfExplode = rightOfExplode.getByIndex(toRightOfExplode.get(4));
                    }
                    rightOfExplode.setValue(rightOfExplode.getValue() + toExplode.getSecondElement().getValue());
                }
                toExplode.setRegular();
                toExplode.setValue(0);
            } else if (snailToSplit != null) {
                snailToSplit.setNotRegular();
            }
        } while (explodeIndexes != null || snailToSplit != null);
    }

    public long getMagnitude() {
        long result = 0;
        if (isRegular) {
            return value;
        } else {
            return 3 * firstElement.getMagnitude() + 2 * secondElement.getMagnitude();
        }
    }

    public void setNotRegular() {
        setRegular(false);
        firstElement = new SnailNumber((int) Math.floor(value/2.0));
        secondElement = new SnailNumber((int) Math.ceil(value/2.0));
        value = null;
    }

    public void setRegular() {
        setRegular(true);
        firstElement = null;
        secondElement = null;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setRegular(boolean isRegular) {
        this.isRegular = isRegular;
    }

    public List<Integer> shouldExplode(List<List<Integer>> paths) {
        for (int i = 0; i < paths.size(); i++) {
            if (paths.get(i).size() == 5) {
                paths.get(i).add(i + 1);
                return paths.get(i);
            }
        }
        return null;
    }


}
