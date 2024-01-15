package arithmetic;

enum PlusOrMinusOne {
    PLUS,
    MINUS;

    protected int getValue() {
        if (this == PlusOrMinusOne.MINUS)
            return -1;
        return 1;
    }

    public String toString() {
        if (this == PlusOrMinusOne.MINUS)
            return "-1";
        return "1";
    }
}
