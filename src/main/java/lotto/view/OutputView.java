package lotto.view;


import java.text.MessageFormat;
import java.util.List;
import lotto.model.Lotto;

public class OutputView {
    public static final String RESULT_HEADER_TITLE = "당첨 통계";
    public static final String RESULT_HEADER_RULE  = "---";

    private static final String PURCHASE_COUNT_FMT = "{0,number,integer}개를 구매했습니다.";
    private static final String RETURN_RATE_FMT = "총 수익률은 {0,number,0.0}%입니다.";

    public void printPurchaseCount(int count) {
        System.out.println(MessageFormat.format(PURCHASE_COUNT_FMT, count));
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    public void printResultHeader() {
        System.out.println();
        System.out.println(RESULT_HEADER_TITLE);
        System.out.println(RESULT_HEADER_RULE);
    }

    public void printReturnRate(double ratePercent) {
        System.out.println(MessageFormat.format(RETURN_RATE_FMT, ratePercent));
    }
}
