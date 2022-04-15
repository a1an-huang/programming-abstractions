import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * @author Alan Huang
 */
public class SquareSymmetries implements Symmetries<Square> {

    public boolean areSymmetric(Square s1, Square s2) {
        List<Square> collection = symmetriesOf(s1);
        List<Point> sq2 = s2.vertices();
        for (int i = 0; i < collection.size(); i++) {
            List<Point> sq1 = collection.get(i).vertices();
            int equalPoints = 0;
            for (int j = 0; j < sq1.size(); j++) {
                Point p1 = sq1.get(j);
                Point p2 = sq2.get(j);
                double p1x = p1.getX();
                double p1y = p1.getY();
                double p2x = p2.getX();
                double p2y = p2.getY();
                if (Math.abs(p1x - p2x) < 0.001 && Math.abs(p1y - p2y) < 0.001) {
                    equalPoints++;
                }
                if (equalPoints == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Square> symmetriesOf(Square s) {
        List<Square> collection = new ArrayList<Square>();
        for (int i = 0; i < 4; i++) {
            collection.add(s.rotateBy(i * 90));
        }
        List<Point> lst = s.vertices();
        Square t1 = new Square(lst.get(3), lst.get(2), lst.get(1), lst.get(0));
        for (int i = 0; i < 4; i++) {
            collection.add(t1.rotateBy(i * 90));
        }
        return collection;
    }
}
