package lotto.model;

import java.util.Arrays;
import java.util.function.BiPredicate;

// 각 등수가 가진 당첨 조건(일치하는 번호 수, 보너스번호 보유 여부)과 그 금액을 묶어서 관리하는 enum
public enum Rankings {
    FIRST(6,
            false,
          2000000000,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount.equals(6)),
    SECOND(5,
            true,
            30000000,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount.equals(5) && hasBonusNumber.equals(true)),
    THIRD(5,
            false,
            1500000,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount.equals(5) && hasBonusNumber.equals(false)),
    FOURTH(4,
            false,
            50000,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount.equals(4)),
    FIFTH(3,
            false,
            5000,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount.equals(3)),
    NONE(0,
            false,
            0,
            (matchedNumberCount, hasBonusNumber) -> matchedNumberCount < 3);

    private final int matchedNumberCount;
    private final boolean hasBonusNumber;
    private final int price;
    // 일치하는 번호 수, 보너스 번호 보유 여부 값을 입력받아 boolean 값을 반환하는 검사 함수
    private final BiPredicate<Integer, Boolean> winningCondition;

    Rankings(final int matchedNumberCount, final boolean hasBonusNumber, final int price, final BiPredicate<Integer, Boolean> winningCondition) {
        this.matchedNumberCount = matchedNumberCount;
        this.hasBonusNumber = hasBonusNumber;
        this.price = price;
        this.winningCondition = winningCondition;
    }

    public int getMatchedNumberCount() {
        return this.matchedNumberCount;
    }
    public boolean hasBonusNumber() {
        return this.hasBonusNumber;
    }
    public int getPrice() {
        return this.price;
    }
    // 외부에서 요청을 받아 랭킹 조건에 맞는 랭킹 상수를 반환
    public static Rankings findRanking(final int matchedNumberCount, final boolean hasBonusNumber) {
        return Arrays.stream(Rankings.values())
                .filter(match -> match.winningCondition.test(matchedNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(NONE);
    }
}
