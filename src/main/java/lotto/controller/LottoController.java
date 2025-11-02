package lotto.controller;

import java.util.List;
import java.util.Map;

import java.util.function.Supplier;
import lotto.model.BonusNumber;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.LottosFactory;
import lotto.model.Rankings;
import lotto.model.WinningNumbers;
import lotto.model.WinningResult;
import lotto.view.InputStringParser;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.view.ResultFormatter;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottosFactory lottosFactory;

    public LottoController(InputView inputView, OutputView outputView, LottosFactory lottosFactory) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottosFactory = lottosFactory;
    }

    public void run() {
        // 금액을 입력받고 구매한 로또 출력
        Lottos lottos = readPaymentAmountAndCreateLottos();
        printPurchaseInfo(lottos);

        // 당첨 번호와 보너스 번호를 입력받아 결과 생성
        WinningNumbers winningNumbers = readWiningNumbers();
        WinningResult result = buildResult(lottos, winningNumbers);

        // 생성한 결과 및 최종 수익률 출력
        printStatistics(result, lottos);
    }

    private Lottos readPaymentAmountAndCreateLottos() {
        return retryUntilValid(()-> {
            int amount = InputStringParser.stringToInteger(inputView.readPaymentAmount());
            return lottosFactory.createFrom(amount);
        });
    }

    private void printPurchaseInfo(Lottos lottos) {
        outputView.printPurchaseCount(lottos.countLottos());
        outputView.printLottos(lottos.getLottos());
    }

    private WinningNumbers readWiningNumbers() {
        return retryUntilValid(() -> {
            List<Integer> mains = InputStringParser.stringsToIntegers(inputView.readWiningNumbers());
            Lotto mainNumbers = Lotto.from(mains);

            int bonusNumberValue = InputStringParser.stringToInteger(inputView.readBonusNumber());
            BonusNumber bonusNumber = new BonusNumber(bonusNumberValue);

            return WinningNumbers.of(mainNumbers, bonusNumber);
        });
    }

    private WinningResult buildResult(Lottos lottos, WinningNumbers winningNumbers) {
        return WinningResult.of(lottos, winningNumbers);
    }

    private void printStatistics(WinningResult result, Lottos lottos) {
        outputView.printResultHeader();
        Map<Rankings, Integer> counts = ResultFormatter.orderedCountMap(result);
        outputView.printResultStatistics(counts);
        outputView.printReturnRate(result.calculateReturnRate(lottos));

    }

    // 실패한 경우 메시지를 출력하고 계속해서 값을 입력받기 위한 메서드
    private <T> T retryUntilValid(Supplier<T> step) {
        while (true) {
            try {
                return step.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
