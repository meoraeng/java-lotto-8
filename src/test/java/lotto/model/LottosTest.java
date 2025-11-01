package lotto.model;



import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("생성 시 방어적 복사 테스트")
    void constructorDefensiveCopy() {
        // given
        List<Lotto> source = new ArrayList<>();
        source.add(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        // when
        Lottos lottos = new Lottos(source);

        // then
        source.add(new Lotto(List.of(7, 8, 9, 10, 11, 12))); // 불변 객체가 아니라면 여기서 size가 2로 증가
        assertThat(lottos.getLottos()).hasSize(1);
    }

    @Test
    @DisplayName("getLottos()는 불변으로 스냅샷을 반환")
    void getLottosReturnsImmutableSnapShots() {
        Lottos lottos = new Lottos(List.of(
                new Lotto(
                        List.of(1, 2, 3, 4, 5, 6)
                )
        ));
        // 불변으로 반환된 복사본에 add 시도를 하면 연산 예외 발생
        assertThatThrownBy(() -> lottos.getLottos().add(new Lotto(List.of(7, 8, 9, 10, 11, 12))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
