package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("생성 시 방어적 복사: 원본 리스트 변경이 내부 상태에 영향 없음")
    void constructorDefensiveCopy() {
        // given
        List<Lotto> source = new ArrayList<>();
        source.add(Lotto.from(List.of(1, 2, 3, 4, 5, 6)));

        // when
        Lottos lottos = new Lottos(source);

        // then (원본 변경)
        source.add(Lotto.from(List.of(7, 8, 9, 10, 11, 12)));
        assertThat(lottos.getLottos()).hasSize(1);
    }

    @Test
    @DisplayName("getLottos()는 불변 스냅샷을 반환: add 시도 시 예외")
    void getLottosReturnsImmutableSnapshots() {
        // given
        Lottos lottos = new Lottos(List.of(
                Lotto.from(List.of(1, 2, 3, 4, 5, 6))
        ));

        // then
        assertThatThrownBy(() ->
                lottos.getLottos().add(Lotto.from(List.of(7, 8, 9, 10, 11, 12)))
        ).isInstanceOf(UnsupportedOperationException.class);
    }
}
