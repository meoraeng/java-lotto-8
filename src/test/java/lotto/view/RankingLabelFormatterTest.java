package lotto.view;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.model.Rankings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankingLabelFormatterTest {

    @Test
    @DisplayName("3개 일치 라벨 생성")
    void labelForFifth() {
        assertThat(RankingLabelFormatter.labelOf(Rankings.FIFTH))
                .isEqualTo("3개 일치 (5,000원)");
    }

    @Test
    @DisplayName("4개 일치 라벨 생성")
    void labelForFourth() {
        assertThat(RankingLabelFormatter.labelOf(Rankings.FOURTH))
                .isEqualTo("4개 일치 (50,000원)");
    }

    @Test
    @DisplayName("5개 일치 라벨 생성 (보너스 없음)")
    void labelForThird() {
        assertThat(RankingLabelFormatter.labelOf(Rankings.THIRD))
                .isEqualTo("5개 일치 (1,500,000원)");
    }

    @Test
    @DisplayName("5개 일치 + 보너스 볼 일치 라벨 생성")
    void labelForSecond() {
        assertThat(RankingLabelFormatter.labelOf(Rankings.SECOND))
                .isEqualTo("5개 일치, 보너스 볼 일치 (30,000,000원)");
    }

    @Test
    @DisplayName("6개 일치 라벨 생성")
    void labelForFirst() {
        assertThat(RankingLabelFormatter.labelOf(Rankings.FIRST))
                .isEqualTo("6개 일치 (2,000,000,000원)");
    }
}
