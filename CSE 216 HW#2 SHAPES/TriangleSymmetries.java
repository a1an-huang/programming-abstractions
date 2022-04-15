import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alan Huang
 */
public class TriangleSymmetries implements Symmetries<EqTriangle> {

    public boolean areSymmetric(EqTriangle t1, EqTriangle t2) {
        List<EqTriangle> collection = symmetriesOf(t1);
        List<Point> tri2 = t2.vertices();
        for (int i = 0; i < collection.size(); i++) {
            List<Point> tri1 = collection.get(i).vertices();
            int equalPoints = 0;
            for (int j = 0; j < tri1.size(); j++) {
                Point p1 = tri1.get(j);
                Point p2 = tri2.get(j);
                double p1x = p1.getX();
                double p1y = p1.getY();
                double p2x = p2.getX();
                double p2y = p2.getY();
                if (Math.abs(p1x - p2x) < 0.001 && Math.abs(p1y - p2y) < 0.001) {
                    equalPoints++;
                }
                if (equalPoints == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<EqTriangle> symmetriesOf(EqTriangle t) {
        List<EqTriangle> collection = new ArrayList<EqTriangle>();
        for (int i = 0; i < 3; i++) {
            collection.add(t.rotateBy(i * 120));
        }
        List<Point> lst = t.vertices();
        EqTriangle t1 = new EqTriangle(lst.get(0), lst.get(2), lst.get(1));
        for (int i = 0; i < 3; i++) {
            collection.add(t1.rotateBy(i * 120));
        }
        return collection;
    }
}
