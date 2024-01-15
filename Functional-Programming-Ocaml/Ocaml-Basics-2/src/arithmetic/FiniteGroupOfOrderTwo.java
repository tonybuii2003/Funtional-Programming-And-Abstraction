package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {
    public FiniteGroupOfOrderTwo() {

    }

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne u, PlusOrMinusOne v) {
        if (u.getValue() * v.getValue() == 1) {
            return PlusOrMinusOne.PLUS;
        }
        return PlusOrMinusOne.MINUS;

    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.PLUS;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne t) {
        return t;
    }

}
