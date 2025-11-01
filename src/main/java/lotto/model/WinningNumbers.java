package lotto.model;

import lotto.global.exception.ErrorMessage;

public class WinningNumbers {
    private final Lotto mainNumbers;
    private final BonusNumber bonusNumber;

    private WinningNumbers(Lotto mainNumbers, BonusNumber bonusNumber) {
        validateUniqueBonusNumber(mainNumbers, bonusNumber);
        this.mainNumbers = mainNumbers;
        this.bonusNumber = bonusNumber;
    }

    // 사용자에게 입력 받은 당첨 번호를 통해 객체를 생성하는 메서드
    public static WinningNumbers of(Lotto mainNumbers, BonusNumber bonusNumber) {
        return new WinningNumbers(mainNumbers, bonusNumber);
    }

    private static void validateUniqueBonusNumber(Lotto mainNumbers, BonusNumber bonusNumber) {
        if(mainNumbers.contains(bonusNumber.getValue())){
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_NUMBER_ERROR.getMessage());
        }
    }
    public Lotto getMainNumbers() {
        return mainNumbers;
    }
    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }
}
