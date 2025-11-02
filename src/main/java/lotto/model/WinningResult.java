package lotto.model;

import static lotto.global.constants.NumberType.LOTTO_COST;

import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class WinningResult {
    // 결과 데이터 생성 및 초기화 로직
    private Map<Rankings, Integer> winningResult;

    private WinningResult(final Map<Rankings, Integer> winningResult) {
        this.winningResult = winningResult;
    }

    private WinningResult(final Lottos loots, final WinningNumbers winningNumbers) {
        this.winningResult = new EnumMap<>(Rankings.class);
        initializeResult();
        generateResult(loots, winningNumbers);
    }

    public static WinningResult from(final Map<Rankings, Integer> winningResult) {
        return new WinningResult(winningResult);
    }

    public static WinningResult of(final Lottos lottos, final WinningNumbers winningNumbers) {
        return new WinningResult(lottos , winningNumbers);
    }
    // 당첨 내역 객체를 0으로 초기화
    private void initializeResult() {
        for (Rankings rankings : Rankings.values()) {
            winningResult.put(rankings, 0);
        }
    }
    // Lottos의 count와 보너스 번호 보유 여부를 통해 객체 상태 갱신
    private void generateResult(final Lottos lottos, final WinningNumbers winningNumbers) {
        Lotto mainNumbers = winningNumbers.getMainNumbers();
        BonusNumber bonusNumber = winningNumbers.getBonusNumber();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.countMatches(mainNumbers);
            boolean hasBonus = lotto.contains(bonusNumber.getValue());

            Rankings ranking = Rankings.findRanking(matchCount, hasBonus);
            countRankings(ranking);
        }
    }

    private void countRankings(final Rankings rankings) {
        winningResult.put(rankings,  winningResult.getOrDefault(rankings, 0) + 1);
    }
    // 당첨 등수에 대한 통계를 반환하는 메서드(몇 번 당첨되었는지)
    public Integer getValue(final Rankings rankings) {
        return winningResult.get(rankings);
    }


    /**
     * 등수 정보 기반 총 수익률 계산 로직
     *
     * @param lottos 로또의 수익률 계산을 위한 구매 정보
     * @return 총 수익률
     */
    public double calculateReturnRate(final Lottos lottos) {
        int totalPrice = getTotalPrice();
        int totalPayment = lottos.countLottos() * LOTTO_COST.getValue();

        return (double) totalPayment / totalPrice * 100.0;
    }

    private int getTotalPrice() {
        return winningResult.entrySet()
                .stream()
                .mapToInt(this::calculatePriceByRanking)
                .sum();
    }

    private int calculatePriceByRanking(final Entry<Rankings, Integer> entry) {
        int rakingPrice = entry.getKey().getPrice();
        int rakingCount = entry.getValue();

        return rakingPrice * rakingCount;
    }
}
