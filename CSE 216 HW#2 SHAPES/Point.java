/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values.
 *
 * @author Ritwik Banerjee
 */
public class Point {
    
    private final double x, y;
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    public String toString(){
        return "Point: (" + this.x + ", " + this.y + ")";
    }
}