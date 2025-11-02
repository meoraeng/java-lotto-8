package lotto.model;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static lotto.global.constants.NumberType.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoFactoryTest {

    @Test
    @DisplayName("유효한 결제 금액이면 수량만큼 로또를 생성한다")
    void createFromValidAmountCreatesLottos() {
        // given
        LottosFactory factory = new LottosFactory();

        // when
        Lottos lottos = factory.createFrom(8000);

        // then
        assertThat(lottos.getLottos()).hasSize(8);
    }

    @Test
    @DisplayName("랜덤 값이 고정된 상황에서 로또가 올바르게 생성되는지 검증")
    void createLottosHaveValidNumbersWithRandom() {
        // given
        LottosFactory factory = new LottosFactory();

        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    // when
                    Lottos lottos = factory.createFrom(2000);
                    List<Lotto> generated = lottos.getLottos();

                    // then
                    assertThat(generated.get(0).contains(8)).isTrue();
                    assertThat(generated.get(1).contains(3)).isTrue();
                    assertThat(generated).allSatisfy(lotto ->
                            assertThat(lotto.countMatches(lotto)).isEqualTo(LOTTO_SIZE.getValue())
                    );
                },

                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38)
        );
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
