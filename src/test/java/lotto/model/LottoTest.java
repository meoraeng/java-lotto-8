package lotto.model;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    @DisplayName("로또 숫자 개수 검증 예외")
    void constructorInvalidSizeThrows(){
        List<Integer> wrongSize = List.of(1,2,3,4,5);

        AssertionsForClassTypes.assertThatThrownBy(()-> Lotto.from(wrongSize))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 형식끼리 일치하는 값 개수 계산")
    void countMatchesReturnsCorrectCount() {
        // given
        Lotto randomLotto = Lotto.from(List.of(1,2,3,4,5,6));
        Lotto winningNumbers = Lotto.from(List.of(3,4,5,6,7,8));

        // when
        int matches = randomLotto.countMatches(winningNumbers);

        // then
        assertThat(matches).isEqualTo(4);
    }

    @Test
    @DisplayName("contains 동작 확인")
    void containsWorks() {
        // given
        Lotto lotto = Lotto.from(List.of(11, 12, 13, 14, 15, 16));

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
        Lotto lotto = Lotto.from(nums);


        // then
        assertThat(lotto).isNotNull();
        assertThat(lotto).isInstanceOf(Lotto.class);
    }

    @Test
    @DisplayName("무작위 순서로 들어온 리스트도 오름차순으로 정렬")
    void returnSortedNumbers() {
        // given
        List<Integer> nums = List.of(3, 2, 4, 5, 1, 6);
        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = Lotto.from(nums);


        // then
        assertThat(lotto.asList()).isEqualTo(expected);
    }
    
}
