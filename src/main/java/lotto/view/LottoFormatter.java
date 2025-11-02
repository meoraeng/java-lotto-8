package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

public class LottoFormatter {
    private static final String PREFIX = "[";
    private static final String SUFFIX = "]";
    private static final String SEPARATOR = ", ";

    public static String format(List<Integer> numbers) {
        return PREFIX + numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SEPARATOR))
                + SUFFIX;
    }
}

