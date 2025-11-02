package lotto.view;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import lotto.model.Rankings;

public class RankingLabelFormatter {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###");

    private static final String DEFAULT_FORMAT = "{0}개 일치 ({1, number}원)";
    private static final String SECOND_FORMAT = "{0}개 일치, 보너스 볼 일치 ({1, number}원)";
    private static final String NONE_LABEL = "";

    private RankingLabelFormatter() {}

    public static String labelOf(Rankings ranking){
        if (ranking == Rankings.NONE) {
            return NONE_LABEL;
        }

        if (ranking == Rankings.SECOND) {
            return MessageFormat.format(
                    SECOND_FORMAT,
                    ranking.getMatchedNumberCount(),
                    ranking.getPrice());
        }

        return MessageFormat.format(
                DEFAULT_FORMAT,
                ranking.getMatchedNumberCount(),
                ranking.getPrice());
    }
}
