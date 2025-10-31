package lotto.global.constants;

public enum NumberType {
    MIN_LOTTO_NUMBER(1),
    MAX_LOTTO_NUMBER(45),
    LOTTO_SIZE(6),
    LOTTO_COST(1000);

    private final int value;

    NumberType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
