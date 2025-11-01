package lotto.model;

import static lotto.global.constants.NumberType.LOTTO_SIZE;
import static lotto.global.constants.NumberType.MAX_LOTTO_NUMBER;
import static lotto.global.constants.NumberType.MIN_LOTTO_NUMBER;

import java.util.HashSet;
import java.util.List;
import lotto.global.exception.ErrorMessage;

public class Lotto {
    private final List<Integer> numbers;

    private Lotto(List<Integer> numbers) {
        Validator.validate(numbers);
        this.numbers = numbers.stream()
                .sorted()
                .toList();
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

    public List<Integer> asList() {
        return numbers;
    }

    private static class Validator {
        private static void validate(final List<Integer> numbers) {
            validateRange(numbers);
            validateUnique(numbers);
            validateLottoSize(numbers);
        }

        private static void validateRange(final List<Integer> numbers) {
            boolean hasOutOfRange = numbers
                    .stream()
                    .anyMatch(number -> number < MIN_LOTTO_NUMBER.getValue() ||
                            number > MAX_LOTTO_NUMBER.getValue());
            if (hasOutOfRange) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_ERROR.getMessage());
            }
        }

        private static void validateLottoSize(final List<Integer> numbers) {
            if (numbers.size() != LOTTO_SIZE.getValue()) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE_ERROR.getMessage());
            }
        }

        private static void validateUnique(final List<Integer> numbers) {
            if (new HashSet<>(numbers).size() != numbers.size()) {
                throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NUMBER_ERROR.getMessage());
            }
        }
    }
}
