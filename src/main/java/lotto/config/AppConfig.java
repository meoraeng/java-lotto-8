package lotto.config;

import lotto.controller.LottoController;
import lotto.model.LottosFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public static LottoController lottoController() {
        return new LottoController(
                new InputView(),
                new OutputView(),
                new LottosFactory()
        );
    }
}
