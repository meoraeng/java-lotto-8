package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFormatterTest {

    @Test
    @DisplayName("숫자 리스트를 포매터 형식에 맞춰서 변환한다")
    void formatReturnsBracketedCommaSeparatedString() {
        // given
        List<Integer> numbers = List.of(8, 21, 23, 41, 42, 43);

        // when
        String formatted = LottoFormatter.format(numbers);

        // then
        assertThat(formatted).isEqualTo("[8, 21, 23, 41, 42, 43]");
    }
}
