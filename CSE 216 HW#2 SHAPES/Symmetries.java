import java.util.Collection;

/**
 * @author Ritwik Banerjee
 * @since Mar 2022
 */
public interface Symmetries<S extends Shape> {

    boolean areSymmetric(S s1, S s2);

    Collection<S> symmetriesOf(S s);
}
