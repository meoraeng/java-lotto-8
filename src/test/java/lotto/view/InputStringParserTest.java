package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputStringParserTest {

    // stringsToIntegers

    @Test
    @DisplayName("콤마를 구분자로 정수 리스트 파싱 테스트")
    void stringsToIntegersParsesBasicList() {
        // given
        String input = "1,2,3,4,5,6";

        // when
        List<Integer> nums = InputStringParser.stringsToIntegers(input);

        // then
        assertThat(nums).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("콤마 앞뒤 공백을 제거하며 파싱")
    void stringsToIntegersAllowsSpacesAroundComma() {
        // given
        String input = "1,  2 ,   3,4 ,5,   6";

        // when
        List<Integer> nums = InputStringParser.stringsToIntegers(input);

        // then
        assertThat(nums).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    @DisplayName("공백 포함된 빈 입력 예외처리")
    void stringsToIntegersBlankInputThrows() {
        // given
        String input1 = "";
        String input2 = "   ";

        // expect
        assertThatThrownBy(() -> InputStringParser.stringsToIntegers(input1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> InputStringParser.stringsToIntegers(input2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null 입력 예외처리")
    void stringsToIntegersNullThrows() {
        assertThatThrownBy(() -> InputStringParser.stringsToIntegers(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 토큰이 포함되면 예외")
    void stringsToIntegersNonNumberToken_throws() {
        // given
        String input = "1, a, 3";

        // expect
        assertThatThrownBy(() -> InputStringParser.stringsToIntegers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수,양수,0 모두 정수로 파싱 (형식만 검증)")
    void stringsToIntegersAcceptsNegativeAndZero() {
        // given
        String input = "-10,0,25";

        // when
        List<Integer> nums = InputStringParser.stringsToIntegers(input);

        // then
        assertThat(nums).containsExactly(-10, 0, 25);
    }

    // stringToInteger

    @Test
    @DisplayName("단일 정수 파싱 테스트")
    void stringToIntegerParsesSingleInt() {
        // given
        String input = "123";

        // when
        int n = InputStringParser.stringToInteger(input);

        // then
        assertThat(n).isEqualTo(123);
    }

    @Test
    @DisplayName("앞뒤 공백을 제거하고 단일 정수 파싱")
    void stringToIntegerTrimsSpaces() {
        // given
        String input = "   777  ";

        // when
        int n = InputStringParser.stringToInteger(input);

        // then
        assertThat(n).isEqualTo(777);
    }

    @Test
    @DisplayName("빈 입력,공백,널값 예외 단일 정수 파싱")
    void stringToIntegerBlankOrNullThrows() {
        assertThatThrownBy(() -> InputStringParser.stringToInteger(""))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> InputStringParser.stringToInteger("   "))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> InputStringParser.stringToInteger(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 형식이 아닌 값은 예외")
    void stringToIntegerNonNumber_throws() {
        assertThatThrownBy(() -> InputStringParser.stringToInteger("abc"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> InputStringParser.stringToInteger("12a3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
