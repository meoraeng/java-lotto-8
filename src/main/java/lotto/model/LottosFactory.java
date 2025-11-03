package lotto.model;

import static lotto.global.constants.NumberType.LOTTO_COST;
import static lotto.global.constants.NumberType.LOTTO_SIZE;
import static lotto.global.constants.NumberType.MAX_LOTTO_NUMBER;
import static lotto.global.constants.NumberType.MIN_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.global.exception.ErrorMessage;


public class LottosFactory {
    public Lottos createFrom(int paymentAmount) {
        validatePayAmountPositive(paymentAmount);
        validatePaymentUnit(paymentAmount);

        int quantity = calculateQuantity(paymentAmount);
        List<Lotto> lottos = new ArrayList<>();

        generateRandomNumberLotto(quantity, lottos);

        return new Lottos(lottos);
    }

    private void generateRandomNumberLotto(int quantity, List<Lotto> lottos) {
        for(int i = 0; i < quantity; i++){
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                    MIN_LOTTO_NUMBER.getValue(),
                    MAX_LOTTO_NUMBER.getValue(),
                    LOTTO_SIZE.getValue());
            lottos.add(Lotto.from(numbers));
        }
    }

    private int calculateQuantity(int paymentAmount) {
        return paymentAmount / LOTTO_COST.getValue();
    }

    private void validatePayAmountPositive(int paymentAmount) {
        if (paymentAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_RANGE_ERROR.getMessage());
        }
    }

    private void validatePaymentUnit(int paymentAmount) {
        if (paymentAmount % LOTTO_COST.getValue() != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_COST_ERROR.getMessage());
        }
    }
}
