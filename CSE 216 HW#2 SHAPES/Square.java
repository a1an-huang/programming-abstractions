import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The class impementing squares.
 * Note: you can add more methods if you want, but additional methods must be
 * <code>private</code> or <code>protected</code>.
 *
 * @author {Alan Huang}
 */
public class Square implements Shape {

    /**
     * The constructor accepts an array of <code>Point</code>s to form the vertices
     * of the square. If more than four
     * points are provided, only the first four are considered by this constructor.
     * If less than four points are
     * provided, or if the points do not form a valid square, the constructor throws
     * <code>java.lang.IllegalArgumentException</code>.
     *
     * @param vertices the array of vertices (i.e., <code>Point</code> instances)
     *                 provided to the constructor.
     */
    List<Point> list = new ArrayList<Point>();

    public Square(Point... vertices) {
        try {
            for (int i = 0; i < 4; i++) {
                list.add(vertices[i]);
            }
            // list.sort(new Counterclockwise());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid number of vertices");
        }

        if (!isMember(list)) {
            throw new IllegalArgumentException("Not a valid square");
        }

    }

    /**
     * Checks if the series of <code>Point</code> instances form a valid square if
     * the first four form the vertices of
     * the square. This method considers the points in a counterclockwise manner
     * starting with the vertex with the least
     * x-value. If multiple vertices have the same x-value, the counterclockwise
     * ordering starts at the vertex with the
     * least y-value amongst them.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the first four vertices in the argument form a
     *         valid square, and <code>false</code>
     *         otherwise.
     */
    @Override
    public boolean isMember(List<Point> vertices) {
        if (vertices.size() != 4) {
            return false;
        }
        double xCen = (vertices.get(0).getX() + vertices.get(1).getX() + vertices.get(2).getX()
                + vertices.get(3).getX()) / 4;
        double yCen = (vertices.get(0).getY() + vertices.get(1).getY() + vertices.get(2).getY()
                + vertices.get(3).getY()) / 4;
        List<Point> newList = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            newList.add(new Point(vertices.get(i).getX() - xCen, vertices.get(i).getY() - yCen));
        }
        newList.sort(new Counterclockwise());
        Point p1 = newList.get(0), p2 = newList.get(1), p3 = newList.get(2), p4 = newList.get(3);
        double d1 = Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
        double d2 = Math.sqrt(Math.pow(p3.getX() - p2.getX(), 2) + Math.pow(p3.getY() - p2.getY(), 2));
        double d3 = Math.sqrt(Math.pow(p4.getX() - p3.getX(), 2) + Math.pow(p4.getY() - p3.getY(), 2));
        double d4 = Math.sqrt(Math.pow(p1.getX() - p4.getX(), 2) + Math.pow(p1.getY() - p4.getY(), 2));
        double diag1 = Math.sqrt(Math.pow(p3.getX() - p1.getX(), 2) + Math.pow(p3.getY() - p1.getY(), 2));
        double diag2 = Math.sqrt(Math.pow(p2.getX() - p4.getX(), 2) + Math.pow(p2.getY() - p4.getY(), 2));
        for (int i = 0; i < newList.size(); i++) {
            vertices.set(i, new Point(newList.get(i).getX() + xCen, newList.get(i).getY() + yCen));
        }
        return Math.abs(d1 - d2) < 0.00001 && Math.abs(d2 - d3) < 0.00001 && Math.abs(d3 - d4) < 0.00001
                && Math.abs(d4 - d1) < 0.00001 && Math.abs(diag1 - diag2) < 0.00001;// TODO
    }

    @Override
    public int numberOfSides() {
        return 4;
    }

    @Override
    public List<Point> vertices() {
        return list.size() == 4 ? list : null; // TODO
    }

    @Override
    public Square rotateBy(int degrees) {
        double xCen = (list.get(0).getX() + list.get(1).getX() + list.get(2).getX() + list.get(3).getX()) / 4;
        double yCen = (list.get(0).getY() + list.get(1).getY() + list.get(2).getY() + list.get(3).getY()) / 4;
        List<Point> newList = new ArrayList<Point>();
        for (int i = 0; i < list.size(); i++) {
            double x = list.get(i).getX() - xCen;
            double y = list.get(i).getY() - yCen;
            double xrot = x * Math.cos(Math.toRadians(degrees)) - y * Math.sin(Math.toRadians(degrees)) + xCen;
            double yrot = x * Math.sin(Math.toRadians(degrees)) + y * Math.cos(Math.toRadians(degrees)) + yCen;
            newList.add(new Point(xrot, yrot));
        }
        return new Square(newList.get(0), newList.get(1), newList.get(2), newList.get(3)); // TODO
    }

    @Override
    public String toString() {
        MathContext m = new MathContext(4);
        return String.format("Square:[" + "("
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
                + ")"
                + ", ("
                + (new BigDecimal((double) Math.round(1000 * list.get(3).getX()) / 1000)).round(m).stripTrailingZeros()
                + ", "
                + (new BigDecimal((double) Math.round(1000 * list.get(3).getY()) / 1000)).round(m).stripTrailingZeros()
                + ")]\n");// TODO
    }
}
