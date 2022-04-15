import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * The class implementing equilateral triangles, i.e., triangles in which all
 * three sides have the same length.
 * Note: you can add more methods if you want, but additional methods must be
 * <code>private</code> or <code>protected</code>.
 *
 * @author {Alan Huang}
 */
public class EqTriangle implements Shape {

    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices
     * of the equilateral triangle. If more
     * than three points are provided, only the first three are considered by this
     * constructor. If less than three
     * points are provided, or if the points do not form a valid equilateral
     * triangle, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances)
     *                 provided to the constructor.
     */
    List<Point> list = new ArrayList<Point>();

    public EqTriangle(Point... vertices) {
        try {
            for (int i = 0; i < 3; i++) {
                list.add(vertices[i]);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid number of vertices");
        }
        if (!isMember(list)) {
            throw new IllegalArgumentException("Not a valid equilateral triangle");
        }
    }

    /**
     * Checks if the series of <code>Point</code> instances form a valid equilateral
     * triangle if first three form the
     * vertices of the triangle.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first three vertices in the argument form a
     *         valid equilateral triangle, and
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        Point p1 = vertices.get(0), p2 = vertices.get(1), p3 = vertices.get(2);
        double d1 = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        double d2 = Math.sqrt(Math.pow(p3.getX() - p2.getX(), 2) + Math.pow(p3.getY() - p2.getY(), 2));
        double d3 = Math.sqrt(Math.pow(p3.getX() - p1.getX(), 2) + Math.pow(p3.getY() - p1.getY(), 2));
        return Math.abs(d1 - d2) < 0.00001 && Math.abs(d2 - d3) < 0.00001 && Math.abs(d3 - d1) < 0.0001;// TODO

    }

    @Override
    public int numberOfSides() {
        return 3;
    }

    @Override
    public List<Point> vertices() {
        return list.size() == 3 ? list : null; // TODO
    }

    @Override
    public EqTriangle rotateBy(int degrees) {
        double xCen = (list.get(0).getX() + list.get(1).getX() + list.get(2).getX()) / 3;
        double yCen = (list.get(0).getY() + list.get(1).getY() + list.get(2).getY()) / 3;
        List<Point> newList = new ArrayList<Point>();
        for (int i = 0; i < list.size(); i++) {
            double x = list.get(i).getX() - xCen;
            double y = list.get(i).getY() - yCen;
            double xrot = x * Math.cos(Math.toRadians(degrees)) - y * Math.sin(Math.toRadians(degrees)) + xCen;
            double yrot = x * Math.sin(Math.toRadians(degrees)) + y * Math.cos(Math.toRadians(degrees)) + yCen;
            newList.add(new Point(xrot, yrot));
        }
        return new EqTriangle(newList.get(0), newList.get(1), newList.get(2)); // TODO
    }

    public String toString() {
        MathContext m = new MathContext(4);
        return String.format("Triangle:[" + "("
                + (new BigDecimal((double) Math.round(1000 * list.get(0).getX()) / 1000)).round(m).stripTrailingZeros()
                + ", "
                + (new BigDecimal((double) Math.round(1000 * list.get(0).getY()) / 1000)).round(m).stripTrailingZeros()
                + ")"
                + ", ("
                + (new BigDecimal((double) Math.round(1000 * list.get(1).getX()) / 1000)).round(m).stripTrailingZeros()
                + ", "
                + (new BigDecimal((double) Math.round(1000 * list.get(1).getY()) / 1000)).round(m).stripTrailingZeros()
                + ")"
                + ", ("
                + (new BigDecimal((double) Math.round(1000 * list.get(2).getX()) / 1000)).round(m).stripTrailingZeros()
                + ", "
                + (new BigDecimal((double) Math.round(1000 * list.get(2).getY()) / 1000)).round(m).stripTrailingZeros()
                + ")]\n");// TODO
    }
}
