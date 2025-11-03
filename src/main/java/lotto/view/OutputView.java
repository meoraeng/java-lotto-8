package lotto.view;


import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.Rankings;

public class OutputView {
    public static final String RESULT_HEADER_TITLE = "당첨 통계";
    public static final String RESULT_HEADER_RULE  = "---";

    private static final String PURCHASE_COUNT_FORMAT = "{0,number,integer}개를 구매했습니다.";
    private static final String STATSTICS_LINE_FORMAT      = "{0} - {1,number,integer}개";
    private static final String RETURN_RATE_FORMAT = "총 수익률은 {0,number,0.0}%입니다.";

    // enum 데이터의 출력 순서 표현을 위한 상수 리스트
    public static final List<Rankings> RESULT_ORDER = List.of(
            Rankings.FIFTH,
            Rankings.FOURTH,
            Rankings.THIRD,
            Rankings.SECOND,
            Rankings.FIRST
    );

    public void printPurchaseCount(int count) {
        System.out.println(MessageFormat.format(PURCHASE_COUNT_FORMAT, count));
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(LottoFormatter.format(lotto.asList()));
        }
    }

    public void printResultHeader() {
        System.out.println();
        System.out.println(RESULT_HEADER_TITLE);
        System.out.println(RESULT_HEADER_RULE);
    }

    public void printResultStatistics(Map<Rankings, Integer> rankingCount) {
        for (Rankings ranking : RESULT_ORDER) {
            String label = RankingLabelFormatter.labelOf(ranking);
            int count = rankingCount.getOrDefault(ranking, 0);
            System.out.println(MessageFormat.format(STATSTICS_LINE_FORMAT, label, count));
        }
    }

    public void printReturnRate(double ratePercent) {
        System.out.println(MessageFormat.format(RETURN_RATE_FORMAT, ratePercent));
    }
}
