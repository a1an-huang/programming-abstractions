import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alan Huang
 * @param <T>
 */

public class BijectionGroup<T> {

    public static <T> ArrayList<ArrayList<T>> permutation(ArrayList<T> values) {
        ArrayList<ArrayList<T>> perms = new ArrayList<>();
        if (values.isEmpty()) {
            perms.add(new ArrayList<>());
            return perms;
        }
        T first = values.remove(0);
        ArrayList<ArrayList<T>> permutations = permutation(values);
        for (ArrayList<T> permutated : permutations) {
            for (int i = 0; i <= permutated.size(); i++) {
                ArrayList<T> newPerm = new ArrayList<>(permutated);
                newPerm.add(i, first);
                perms.add(newPerm);
            }
        }
        return perms;
    }

    public static <T> Set<UnaryOperator<T>> bijectionsOf(Set<T> domain) {
        Set<UnaryOperator<T>> bijections = new HashSet<>();
        ArrayList<T> values = new ArrayList<>();
        values.addAll(domain);
        ArrayList<ArrayList<T>> perms = permutation(values);
        values.addAll(domain);
        for (ArrayList<T> perm : perms) {
            HashMap<T, T> map = new HashMap<>();
            for (int i = 0; i < perm.size(); i++) {
                map.put(perm.get(i), values.get(i));
            }
            bijections.add(t -> map.get(t));
        }
        return bijections;
    }

    public static <T> Group<UnaryOperator<T>> bijectionGroup(Set<T> domain) {
        Group<UnaryOperator<T>> group = new Group<UnaryOperator<T>>() {
            Set<UnaryOperator<T>> bijections = bijectionsOf(domain);

            @Override
            public UnaryOperator<T> binaryOperation(UnaryOperator<T> one, UnaryOperator<T> other) {
                return (UnaryOperator<T>) one.andThen(other);
            }

            @Override
            public UnaryOperator<T> identity() {
                return t -> t;
            }

            @Override
            public UnaryOperator<T> inverseOf(UnaryOperator<T> f) {
                for (UnaryOperator<T> bijection : bijections) {
                    int counter = 0;
                    for (T t : domain) {
                        if (bijection.apply(t).equals(f.apply(t))) {
                            counter++;
                        }
                    }
                    if (counter == domain.size()) {
                        return bijection;
                    }
                }
                return null;
            }
        };

        return group;
    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        Set<UnaryOperator<Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        Group<UnaryOperator<Integer>> g = bijectionGroup(a_few);
        UnaryOperator<Integer> f1 = bijectionsOf(a_few).stream().findFirst().get();
        UnaryOperator<Integer> f2 = g.inverseOf(f1);
        UnaryOperator<Integer> id = g.identity();

        System.out.println("f1: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f1.apply(n)));
        System.out.println("inverse: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f2.apply(n)));
        System.out.println("identity: ");
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, id.apply(n)));
    }
}
