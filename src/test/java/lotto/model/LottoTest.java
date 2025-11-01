package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
    @Test
    @DisplayName("로또 숫자 개수 검증 예외")
    void constructorInvalidSizeThrows(){
        List<Integer> wrongSize = List.of(1,2,3,4,5);

        assertThatThrownBy(()-> new Lotto(wrongSize))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 형식끼리 일치하는 값 개수 계산")
    void countMatchesReturnsCorrectCount() {
        // given
        Lotto randomLotto = new Lotto(List.of(1,2,3,4,5,6));
        Lotto winningNumbers = new Lotto(List.of(3,4,5,6,7,8));

        // when
        int matches = randomLotto.countMatches(winningNumbers);

        // then
        assertThat(matches).isEqualTo(4);
    }

    @Test
    @DisplayName("contains 동작 확인")
    void containsWorks() {
        // given
        Lotto lotto = new Lotto(List.of(11, 12, 13, 14, 15, 16));

        // expect
        assertThat(lotto.contains(12)).isTrue();
        assertThat(lotto.contains(24)).isFalse();
    }

    @Test
    @DisplayName("from 메서드를 통한 정적 팩토리 생성 가능")
    void fromWorks() {
        // given
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(nums);


        // then
        assertThat(lotto).isNotNull();
        assertThat(lotto).isInstanceOf(Lotto.class);
    }
}
