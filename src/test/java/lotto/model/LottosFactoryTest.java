package lotto.model;

import static lotto.global.constants.NumberType.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    @Test
    @DisplayName("유효한 결제 금액이면 수량만큼 로또를 생성한다")
    void createFromValidAmountCreatesLottos() {
        // given
        LottosFactory factory = new LottosFactory();
        int amount = 8000; // 8장

        // when
        Lottos lottos = factory.createFrom(amount);

        // then
        assertThat(lottos.getLottos()).hasSize(8);
    }

    @Test
    @DisplayName("생성된 각 로또는 유효한 값으로, 번호 개수를 준수하여 생성된다.")
    void createLottosHaveValidNumbers() {
        // given
        LottosFactory factory = new LottosFactory();
        Lottos lottos = factory.createFrom(5000);

        // then
        for (Lotto lotto : lottos.getLottos()) {
            assertThat(lotto.countMatches(lotto)).isEqualTo(LOTTO_SIZE.getValue());
        }
    }

    @Test
    @DisplayName("결제 금액이 0 이하이면 예외")
    void createFromZeroOrNegativeAmountThrows() {
        // given
        LottosFactory factory = new LottosFactory();

        // expect
        assertThatThrownBy(() -> factory.createFrom(0))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> factory.createFrom(-1000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("결제 금액이 1000원 단위가 아니면 예외")
    void createFrom_not_multiple_of_thousand_throws() {
        // given
        LottosFactory factory = new LottosFactory();

        // expect
        assertThatThrownBy(() -> factory.createFrom(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
