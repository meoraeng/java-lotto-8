package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    @DisplayName("당첨번호(6개)와 보너스번호가 겹치지 않으면 정상 생성된다")
    void createWinningNumbersSuccessWhenNoOverlap() {
        // given
        Lotto main = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = new BonusNumber(7);

        // when
        WinningNumbers wn = WinningNumbers.of(main, bonus);

        // then
        assertThat(wn).isNotNull();
        assertThat(wn.getMainNumbers()).isSameAs(main);      // 불변 값 그대로 보유
        assertThat(wn.getBonusNumber()).isSameAs(bonus);     // 동일 참조 보유
    }

    @Test
    @DisplayName("보너스 번호가 당첨 6개 중 하나와 중복되면 예외 발생")
    void createWinningNumbersThrowsWhenBonusDuplicate() {
        // given
        Lotto main = Lotto.from(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonus = new BonusNumber(6); // 중복

        // expect
        assertThatThrownBy(() -> WinningNumbers.of(main, bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
