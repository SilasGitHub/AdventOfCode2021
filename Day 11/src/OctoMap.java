import java.util.*;

public class OctoMap {
    private List<Octopus> octopusList = new ArrayList<>();
    private int maxX;
    private int maxY;
    private long numOfFlashes = 0;
    private boolean allOctosFlashed = false;

    public boolean haveAllOctosFlashed() {
        return allOctosFlashed;
    }

    public OctoMap(List<String> input) {
        maxX = input.get(0).length() - 1;
        maxY = input.size() - 1;
        for (int i = 0; i < input.size(); i++) {
            String row = input.get(i);
            for (int j = 0; j < row.length(); j++) {
                octopusList.add(new Octopus(j, i, Integer.parseInt(row.substring(j, j + 1))));
            }
        }
    }

    public Octopus getOcto(int x, int y) {
        return octopusList.get(y * (maxX + 1) + x);
    }

    public boolean octoToFlash() {
        boolean toFlash = false;
        for (Octopus octo : octopusList) {
            if (octo.getValue() > 9) {
                toFlash = true;
            }
        }
        return toFlash;
    }

    public List<Octopus> getNeighbourOctos(Octopus octo) {
        List<Octopus> result = new ArrayList<>();
        if (octo.getX() != 0) {
            result.add(getOcto(octo.getX() - 1, octo.getY()));
            if (octo.getY() != 0) {
                result.add(getOcto(octo.getX() - 1, octo.getY() - 1));
            }
            if (octo.getY() != maxY) {
                result.add(getOcto(octo.getX() - 1, octo.getY() + 1));
            }
        }
        if (octo.getY() != 0) {
            result.add(getOcto(octo.getX(), octo.getY() - 1));
        }
        if (octo.getY() != maxY) {
            result.add(getOcto(octo.getX(), octo.getY() + 1));
        }
        if (octo.getX() != maxX) {
            result.add(getOcto(octo.getX() + 1, octo.getY()));
            if (octo.getY() != 0) {
                result.add(getOcto(octo.getX() + 1, octo.getY() - 1));
            }
            if (octo.getY() != maxY) {
                result.add(getOcto(octo.getX() + 1, octo.getY() + 1));
            }
        }
        return result;
    }

    public void nextStep() {
        Set<Octopus> allChargedOcto = new HashSet<>();
        for (Octopus octo : octopusList) {
            octo.increaseValue();
        }
        while(octoToFlash()) {
            List<Octopus> chargedOcto = new ArrayList<>();
            for (Octopus octo : octopusList) {
                if (octo.getValue() > 9) {
                    chargedOcto.add(octo);
                    allChargedOcto.add(octo);
                }
            }
            Map<Octopus, Integer> toCharge = new HashMap<>();
            for (Octopus octo : chargedOcto) {
                for (Octopus octoToCharge : getNeighbourOctos(octo)) {
                    toCharge.put(octoToCharge, toCharge.getOrDefault(octoToCharge, 0) + 1);
                }
            }
            for (Octopus octo : allChargedOcto) {
                toCharge.remove(octo);
                if (chargedOcto.contains(octo)) {
                    octo.setValue(0);
                    numOfFlashes++;
                }
            }
            for (Map.Entry<Octopus, Integer> entry : toCharge.entrySet()) {
                entry.getKey().setValue(entry.getKey().getValue() + entry.getValue());
            }
        }
        if (allChargedOcto.containsAll(octopusList)) {
            allOctosFlashed = true;
        }
    }

    public long getNumOfFlashes() {
        return numOfFlashes;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Octopus octo : octopusList) {
            result.append(octo.getValue());
            if (octo.getX() == maxX) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
