
/**
 * @author Alan Huang
 */
import java.util.Comparator;

public class Counterclockwise implements Comparator<Point> {
    public int compare(Point p1, Point p2) {
        double p1d = Math.atan2(p1.getY(), p1.getX());
        double p2d = Math.atan2(p2.getY(), p2.getX());
        return p1d > p2d ? 1 : -1;
    }
}
