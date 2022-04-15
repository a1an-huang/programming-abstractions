import java.util.List;

/**
 * This interface defines the basic properties expected from a shape.
 * !! DO NOT CHANGE THIS CODE !!
 *
 * @author Ritwik Banerjee
 */
public interface Shape {

    /**
     * Checks if the series of <code>Point</code> instances form a valid instance of
     * this shape, where each vertex forms
     * one "corner" of the shape. In general, the order of the vertices matter in
     * deciding whether they form a valid
     * instance of a particular shape.
     *
     * @param vertices the list of specified vertices.
     * @return <code>true</code> if the vertices in the argument form a valid
     *         instance of this shape, and
     *         <code>false</code> otherwise.
     */
    boolean isMember(List<Point> vertices);

    /**
     * @return the number of sides of this shape.
     */
    int numberOfSides();

    /**
     * @return the vertices of this shape as a list of <code>Point</code>s.
     */
    List<Point> vertices();

    /**
     * Rotate this shape by the specified number of radians, theta, <i>around the
     * center of this shape</i>. In two
     * dimensions, the counterclockwise rotation (around the origin
     * <code>(0,0)</code>) of a point <code>(x, y)</code>
     * is given by a new point <code>(x', y')</code>, where
     * <p>
     * 
     * <pre>
     *         x' = x cos(theta) - y sin(theta)
     *         y' = x sin(theta) + y cos(theta)
     * </pre>
     * </p>
     * This formula requires the argument of this method to be converted from
     * degrees to radians.
     *
     * @param degrees the number of degrees of counterclockwise rotation
     * @return the rotated shape.
     */
    Shape rotateBy(int degrees);
}