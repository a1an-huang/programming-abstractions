/**
 * @author Alan Huang
 */
public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    public FiniteGroupOfOrderTwo() {

    }

    public PlusOrMinusOne binaryOperation(PlusOrMinusOne x, PlusOrMinusOne y) {
        if ((x == PlusOrMinusOne.PLUS && y == PlusOrMinusOne.PLUS)
                || (x == PlusOrMinusOne.MINUS && y == PlusOrMinusOne.MINUS)) {
            return PlusOrMinusOne.PLUS;
        }
        return PlusOrMinusOne.MINUS;
    }

    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.PLUS;
    }

    public PlusOrMinusOne inverseOf(PlusOrMinusOne x) {
        return x == PlusOrMinusOne.PLUS ? PlusOrMinusOne.MINUS : PlusOrMinusOne.PLUS;
    }

    public PlusOrMinusOne exponent(PlusOrMinusOne x, int k) {
        if(k < 0){
            throw new IllegalArgumentException("Exponent cant not be a negative value");
        }
        return k == 0? identity(): binaryOperation(x, exponent(x, k - 1));
    }
}
