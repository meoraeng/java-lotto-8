package lotto.model;

import static lotto.global.constants.NumberType.LOTTO_COST;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    // 의존중인 객체들 임시 생성
    private static Lotto lottoOf(int... nums) {
        return Lotto.from(List.of(nums[0], nums[1], nums[2], nums[3], nums[4], nums[5]));
    }
    // 랜덤 함수 없이 유효한 로또 5장 생성
    private static Lottos lottosOf(int count) {
        List<Lotto> list = new ArrayList<>();
        int base = 1;

        for (int i = 0; i < count; i++) {
            list.add(lottoOf(base, base+1, base+2, base+3, base+4, base+5));
            base += 3; // (1,2,3,4,5,6) / (4,5,6,7,8,9) / (7,8,9,10,11,12) ...
        }

        return new Lottos(list);
    }

    private static WinningNumbers winningNumbersOf() {
        Lotto main = lottoOf(1,2,3,4,5,6);
        BonusNumber bonus = new BonusNumber(7);
        return WinningNumbers.of(main, bonus);
    }




    @Test
    @DisplayName("of()로 Map의 초기값을 0으로 세팅한다")
    void initializeResultToZero() {
        // given
        Lottos lottos = lottosOf(3);
        WinningNumbers winningNumbers = winningNumbersOf();

        // when
        WinningResult result = WinningResult.of(lottos, winningNumbers);

        // then
        assertThat(result.getValue(Rankings.FIRST)).isEqualTo(1);
        assertThat(result.getValue(Rankings.FIFTH)).isEqualTo(1);
        assertThat(result.getValue(Rankings.NONE)).isEqualTo(1);
    }

    @Test
    @DisplayName("from(Map) 경로: 특정 랭킹 카운트 조회 가능")
    void getValueReturnsCorrectCount() {
        // given
        Map<Rankings, Integer> counts = new EnumMap<>(Rankings.class);
        counts.put(Rankings.SECOND, 3);
        counts.put(Rankings.FOURTH, 1);

        WinningResult result = WinningResult.from(counts);

        // then
        assertThat(result.getValue(Rankings.SECOND)).isEqualTo(3);
        assertThat(result.getValue(Rankings.FOURTH)).isEqualTo(1);
    }

    @Test
    @DisplayName("from(Map)으로 총 당첨금 및 수익률 계산 검증")
    void calculateTotalPriceAndReturnRate() {
        // given
        Map<Rankings, Integer> rankingCounts = new EnumMap<>(Rankings.class);
        rankingCounts.put(Rankings.THIRD, 2);
        rankingCounts.put(Rankings.FOURTH, 3);

        WinningResult result = WinningResult.from(rankingCounts);

        Lottos lottos = lottosOf(5);
        int totalPayment = lottos.countLottos() * LOTTO_COST.getValue();

        int expectedTotalPrice =
                Rankings.THIRD.getPrice() * 2
                        + Rankings.FOURTH.getPrice() * 3;

        // when
        double returnRate = result.calculateReturnRate(lottos);

        // then
        double expectedReturnRate = (double) expectedTotalPrice / totalPayment * 100.0;

        assertThat(returnRate).isEqualTo(expectedReturnRate);
    }

    @Test
    @DisplayName("실제 로또와 당첨 번호를 비교하여 당첨 등수를 집계한다")
    void generateResultCountsCorrectly() {
        // given
        Lottos lottos = new Lottos(List.of(
                lottoOf(1,2,3,4,5,6),    // 6개 일치하는 1등
                lottoOf(1,2,3,4,5,7),    // 5개 + 보너스 포함하는 2등
                lottoOf(9,10,11,12,13,14) // 일치하는게 없는 NONE
        ));

        WinningNumbers wn = WinningNumbers.of( // 당첨번호 + 보너스번호 지정
                lottoOf(1,2,3,4,5,6),
                new BonusNumber(7)
        );

        // when
        WinningResult result = WinningResult.of(lottos, wn);

        // then
        assertThat(result.getValue(Rankings.FIRST)).isEqualTo(1);
        assertThat(result.getValue(Rankings.SECOND)).isEqualTo(1);
        assertThat(result.getValue(Rankings.NONE)).isEqualTo(1);
    }
}
