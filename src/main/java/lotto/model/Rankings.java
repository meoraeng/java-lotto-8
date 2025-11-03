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
    // 등수 조건을 충족하는지 체크하는 조건식(2개의 값을 받고 boolean 반환)
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
    // 주어진 매개변수 값에 충족하는 Rankings를 찾아서 반환
    public static Rankings findRanking(final int matchedNumberCount, final boolean hasBonusNumber) {
        return Arrays.stream(Rankings.values())
                .filter(match -> match.winningCondition.test(matchedNumberCount, hasBonusNumber))
                .findFirst()
                .orElse(NONE);
    }
}
