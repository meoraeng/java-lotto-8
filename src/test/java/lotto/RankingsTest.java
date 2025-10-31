package lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class RankingsTest {
    @DisplayName("당첨 등수별 금액을 체크한다.")
    @CsvSource({"NONE, 0",
            "FIFTH, 5000",
            "FOURTH, 50000",
            "THIRD, 1500000",
            "SECOND, 30000000",
            "FIRST, 2000000000",
    })
    @ParameterizedTest
    void rankPriceTest(Rankings rankNumber, int expectedPrice) {
        // when
        int price = rankNumber.getPrice();

        // then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @DisplayName("당첨 등수 별로 뽑힌 숫자 개수, 보너스 번호 존재 여부, 등수별 금액를 체크한다.")
    @CsvSource({
            "NONE, 0, false, 0",
            "FIFTH, 3, false, 5000",
            "FOURTH, 4, false, 50000",
            "THIRD, 5, false, 1500000",
            "SECOND, 5, true, 30000000",
            "FIRST, 6, false, 2000000000",
    })
    @ParameterizedTest
    void rankConditionsTest(Rankings ranking, int matchedNumberCount, boolean hasBonusNum, int expectedPrice) {
        // when
        int count = ranking.getCount();
        boolean bonus = ranking.hasBonusNum();
        int price = ranking.getPrice();

        // then
        assertThat(count).isEqualTo(matchedNumberCount);
        assertThat(bonus).isEqualTo(hasBonusNum);
        assertThat(price).isEqualTo(expectedPrice);
    }
    @DisplayName("당첨 등수 찾기 메소드 잘 동작하는지 체크")
    @ParameterizedTest
    @MethodSource("cases")
    void rankVerifyShouldWork(int matchedNumberCount, boolean hasBonusNum, Rankings expectedRanking ) {
        assertEquals(expectedRanking, Rankings.findRanking(matchedNumberCount,hasBonusNum));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(6, false, Rankings.FIRST),
                Arguments.of(5, true, Rankings.SECOND),
                Arguments.of(5, false, Rankings.THIRD),
                Arguments.of(4, false, Rankings.FOURTH),
                Arguments.of(3, false, Rankings.FIFTH),
                Arguments.of(2, false, Rankings.NONE),
                Arguments.of(1, false, Rankings.NONE)
        );
    }
}
