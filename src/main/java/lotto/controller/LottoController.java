package lotto.controller;

import java.util.List;
import java.util.Map;

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
        // 금액을 입력받고 로또를 생성
        int paymentAmount = InputStringParser.stringToInteger(inputView.readPaymentAmount());
        Lottos lottos = lottosFactory.createFrom(paymentAmount);

        // 구매 결과 출력
        outputView.printPurchaseCount(lottos.countLottos());
        outputView.printLottos(lottos.getLottos());

        // 당첨 번호와 보너스 번호를 입력받아 도메인에 저장
        List<Integer> winNums = InputStringParser.stringsToIntegers(inputView.readWiningNumbers());
        Lotto mainNumbers = Lotto.from(winNums);

        int bonusNumberValue = InputStringParser.stringToInteger(inputView.readBonusNumber());
        BonusNumber bonusNumber = new BonusNumber(bonusNumberValue);

        WinningNumbers winningNumbers = WinningNumbers.of(mainNumbers, bonusNumber);

        // 당첨 결과 생성
        WinningResult result = WinningResult.of(lottos, winningNumbers);
        Map<Rankings, Integer> counts =
                ResultFormatter.orderedCountMap(result);

        // 당첨 결과에 대한 통계 출력
        outputView.printResultHeader();
        outputView.printResultStatistics(counts);
        outputView.printReturnRate(result.calculateReturnRate(lottos));
    }
}
