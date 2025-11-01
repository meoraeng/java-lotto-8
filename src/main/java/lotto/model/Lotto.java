package lotto.model;

import static lotto.global.constants.NumberType.LOTTO_SIZE;

import java.util.List;
import lotto.global.exception.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        Validator.validate(numbers);
        this.numbers = numbers;
    }

    public int countMatches(Lotto other) {
        return (int) numbers.stream().
                filter(other::contains)
                .count();
    }

    public boolean contains(Integer number) {
        return numbers.contains(number);
    }

    public static Lotto from(final List<Integer> numbers) {
        return new Lotto(numbers);
    }


    private static class Validator {
        private static void validate(final List<Integer> numbers) {
            validateLottoSize(numbers);
        }

        private static void validateLottoSize(final List<Integer> numbers) {
            if (numbers.size() != LOTTO_SIZE.getValue()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE_ERROR.getMessage());
            }
        }
    }
}
