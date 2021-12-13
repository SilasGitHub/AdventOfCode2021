public class Fold {
    private boolean horizontal;
    private int lineToFold;

    public Fold(String fold) {
        fold = fold.substring(11);
        horizontal = fold.charAt(0) == 'x';
        lineToFold = Integer.parseInt(fold.substring(2));
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getLineToFold() {
        return lineToFold;
    }
}
