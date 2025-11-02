package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static final String PROMPT_PAYMENT = "구입금액을 입력해 주세요.";
    public static final String PROMPT_WINNING = "당첨 번호를 입력해 주세요.";
    public static final String PROMPT_BONUS = "보너스 번호를 입력해 주세요.";

    public String readLine(String prompt) {
        System.out.println(prompt);
        return Console.readLine();
    }

    public String readPaymentAmount() {
        return readLine(PROMPT_PAYMENT);
    }
    public String readWiningNumbers() {
        return readLine(PROMPT_WINNING);
    }
    public String readBonusNumber() {
        return readLine(PROMPT_BONUS);
    }
}
