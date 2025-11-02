package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lotto.global.exception.ErrorMessage;


public class InputStringParser {
    // 공백까지 체크하고 제거해서 split하도록 표현식 작성
    private static final Pattern COMMA_SEPARATOR_REGEX = Pattern.compile("\\s*,\\s*");

    private InputStringParser() {}

    public static List<Integer> stringsToIntegers(String input) {
        validateNonBlank(input);
        String[] tokens = split(input);
        List<Integer> numbers = new ArrayList<>(tokens.length);
        for (String token : tokens) {
            numbers.add(validateNumberFormat(token));
        }
        return numbers;
    }

    public static int stringToInteger(String input) {
        validateNonBlank(input);
        return validateNumberFormat(input.trim());
    }

    private static String[] split(String input) {
        return COMMA_SEPARATOR_REGEX.split(input);
    }

    private static int validateNumberFormat(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.NOT_NUMBER_INPUT_ERROR.getMessage());
        }
    }

    private static void validateNonBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.BLANK_INPUT_ERROR.getMessage());
        }
    }
}
