package lotto.model;

import static lotto.global.constants.NumberType.MAX_LOTTO_NUMBER;
import static lotto.global.constants.NumberType.MIN_LOTTO_NUMBER;

import lotto.global.exception.ErrorMessage;

public class BonusNumber {
    private Integer value;
    public BonusNumber(final Integer value) {
        validateNumberRange(value);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    private void validateNumberRange(Integer value) {
        if (value < MIN_LOTTO_NUMBER.getValue() || value > MAX_LOTTO_NUMBER.getValue()){
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_ERROR.getMessage());
        }
    }
}
