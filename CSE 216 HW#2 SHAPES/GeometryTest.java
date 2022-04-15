import java.util.List;
import java.util.ArrayList;

/**
 * This class is given to you as an outline for testing your code. You can
 * modify this as you want, but please keep in
 * mind that the lines already provided here as expected to work exactly as they
 * are.
 *
 * @author Ritwik Banerjee
 */
public class GeometryTest {

    public static void main(String... args) {
        testTriangleSymmetries();
        testSquareSymmetries();
    }

    private static void testTriangleSymmetries() {
        // t0 doesn't form a equilateral triangle. the following line is expected to
        // throw an IllegalArgumentException
        // EqTriangle t0 = new EqTriangle(new Point(1, 1), new Point(1, 2), new Point(1,
        // 3));
        List<Point> newList = new ArrayList<Point>();
        newList.add(new Point(1, 1));
        newList.add(new Point(9, 2));
        newList.add(new Point(6, -3));
        newList.add(new Point(-1, 3));
        newList.add(new Point(2, 5));
        newList.add(new Point(3, 4));
        newList.add(new Point(-2, -3));
        newList.add(new Point(-1, 3));

        System.out.println("Triangle Rotations:");
        EqTriangle t1 = new EqTriangle(new Point(0, 0), new Point(2, 0),
                new Point(1, Math.sin(Math.toRadians(60)) * 2));
        EqTriangle t2 = t1.rotateBy(10);
        EqTriangle t3 = t1.rotateBy(120);
        System.out.print(t1.toString() + t2.toString() + t3.toString());
        System.out.println("Triangle areSymmetric?:");
        TriangleSymmetries triangleSymmetries = new TriangleSymmetries();
        System.out.println(triangleSymmetries.areSymmetric(t1, t2)); // expected to return false
        System.out.println(triangleSymmetries.areSymmetric(t1, t3)); // expected to return true
        List<EqTriangle> symmetries = triangleSymmetries.symmetriesOf(t1);
        System.out.println("Triangle Symmetries:");
        // Your code must ensure that t1.toString() returns the following String
        // "EqTriangle: (0, 0), (2, 0), (1, 1.732)"
        // Any non-integer coordinate value must be correctly rounded and represented
        // with three decimal places.
        for (EqTriangle t : symmetries)
            System.out.print(t.toString());
        System.out.println("List of Points:");
        newList.sort(new Counterclockwise());
        System.out.println(newList);
    }

    private static void testSquareSymmetries() {
        System.out.println("Square Rotations:");
        Square s1 = new Square(new Point(1, 1), new Point(1, 2), new Point(0, 2), new Point(0, 1));
        Square s2 = s1.rotateBy(30);
        Square s3 = s1.rotateBy(180);
        System.out.print(s1.toString() + s2.toString() + s3.toString());
        System.out.println("Square areSymmetric?:");
        SquareSymmetries squareSymmetries = new SquareSymmetries();
        System.out.println(squareSymmetries.areSymmetric(s1, s2)); // expected to return false
        System.out.println(squareSymmetries.areSymmetric(s1, s3)); // expected to return true
        List<Square> symmetries = squareSymmetries.symmetriesOf(s1);
        System.out.println("Square Symmetries:");
        // Your code must ensure that s1.toString() returns the following String
        // "Square: (0, 1), (1, 1), (1, 2), (0, 2)"
        // Note that the order of vertices is not what was given in the constructor
        // above, but rather, the
        // counterclockwise ordering.
        // Any non-integer coordinate value must be correctly rounded and represented
        // with three decimal places.
        for (Square s : symmetries)
            System.out.print(s.toString());
    }
}
