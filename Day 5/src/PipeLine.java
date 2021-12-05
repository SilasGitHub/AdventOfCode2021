import java.util.ArrayList;
import java.util.List;

public class PipeLine {

    private Position startPos;
    private Position endPos;

    public PipeLine(String line) {
        String[] firstParse = line.split(" -> ");
        startPos = new Position(Integer.parseInt(firstParse[0].split(",")[0]),
                Integer.parseInt(firstParse[0].split(",")[1]));
        endPos = new Position(Integer.parseInt(firstParse[1].split(",")[0]),
                Integer.parseInt(firstParse[1].split(",")[1]));
    }

    public boolean isHorOrVert() {
        return startPos.getX() == endPos.getX() || startPos.getY() == endPos.getY();
    }

    public List<Position> pipePositions() {
        List<Position> result = new ArrayList<>();
        if (startPos.getX() < endPos.getX() && startPos.getY() == endPos.getY()) {
            for (int i = startPos.getX(); i <= endPos.getX(); i++) {
                result.add(new Position(i, startPos.getY()));
            }
        } else if (startPos.getX() > endPos.getX() && startPos.getY() == endPos.getY()) {
            for (int i = startPos.getX(); i >= endPos.getX(); i--) {
                result.add(new Position(i, startPos.getY()));
            }
        } else if (startPos.getY() < endPos.getY() && startPos.getX() == endPos.getX()) {
            for (int i = startPos.getY(); i <= endPos.getY(); i++) {
                result.add(new Position(startPos.getX(), i));
            }
        } else if (startPos.getY() > endPos.getY() && startPos.getX() == endPos.getX()){
            for (int i = startPos.getY(); i >= endPos.getY(); i--) {
                result.add(new Position(startPos.getX(), i));
            }
        } else if (startPos.getX() < endPos.getX() && startPos.getY() < endPos.getY()) {
            for (int i = 0; i <= (Math.abs(startPos.getX() - endPos.getX())); i++) {
                result.add(new Position(startPos.getX() + i, startPos.getY() + i));
            }
        } else if (startPos.getX() > endPos.getX() && startPos.getY() > endPos.getY()) {
            for (int i = 0; i <= (Math.abs(startPos.getX() - endPos.getX())); i++) {
                result.add(new Position(startPos.getX() - i, startPos.getY() - i));
            }
        } else if (startPos.getX() < endPos.getX() && startPos.getY() > endPos.getY()) {
            for (int i = 0; i <= (Math.abs(startPos.getX() - endPos.getX())); i++) {
                result.add(new Position(startPos.getX() + i, startPos.getY() - i));
            }
        } else if (startPos.getX() > endPos.getX() && startPos.getY() < endPos.getY()) {
            for (int i = 0; i <= (Math.abs(startPos.getX() - endPos.getX())); i++) {
                result.add(new Position(startPos.getX() - i, startPos.getY() + i));
            }
        }
        return result;
    }
}
