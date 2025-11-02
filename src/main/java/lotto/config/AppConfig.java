package lotto.config;

import lotto.model.LottosFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public LottosFactory lottosFactory() {
        return new LottosFactory();
    }
}
