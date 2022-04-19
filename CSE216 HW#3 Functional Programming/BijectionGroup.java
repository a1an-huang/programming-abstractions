import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alan Huang
 */

public class BijectionGroup {

    public static T bijectionOf(Set<T> domain) {

    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        BijectionGroup bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });
    }
}
