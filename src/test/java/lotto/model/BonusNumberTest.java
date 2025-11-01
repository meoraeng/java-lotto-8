package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {

    @Test
    @DisplayName("보너스 번호는 값을 그대로 보유한다(getValue 확인)")
    void bonusNumber_holds_value() {
        // given
        BonusNumber bonus = new BonusNumber(13);

        // expect
        assertThat(bonus.getValue()).isEqualTo(13);
    }

    @Test
    @DisplayName("범위를 벗어난 보너스 번호는 예외가 발생해야 한다 (TDD: 현재는 실패 예상)")
    void bonusNumber_out_of_range_should_throw() {
        assertThatThrownBy(() -> new BonusNumber(0))   // 최소 범위 미만
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new BonusNumber(46))  // 최대 범위 초과
                .isInstanceOf(IllegalArgumentException.class);
    }
}
