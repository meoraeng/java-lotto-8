package lotto.view;

import java.util.LinkedHashMap;
import java.util.Map;
import lotto.model.Rankings;
import lotto.model.WinningResult;

public class ResultFormatter {
    // 출력 요구사항에 맞춰 result 통계 출력 순서를 조정하기 위한 메서드
    public static Map<Rankings, Integer> orderedCountMap(WinningResult winningResult) {
        Map<Rankings, Integer> counts = new LinkedHashMap<>();
        for (Rankings ranking : OutputView.RESULT_ORDER) {
            counts.put(ranking, winningResult.getValue(ranking));
        }
        return counts;
    }
}
