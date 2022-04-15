/**
 * @author Alan Huang
 */
enum PlusOrMinusOne {
    PLUS(1),
    MINUS(-1);

    private int val;

    PlusOrMinusOne(int val) {
        this.val = val;
    }

    public String toString() {
        return this == PLUS ? "1" : "-1";
    }
}
