package nextstep.blackjack.view;

import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_FORMAT = "%s : %.0f";
    private static final String FIRST_TURN = "%s : %s";
    private static final String CARD_AND_SCORE_FORMAT = "%s : %s - 결과: %s";
    public OutputView() {
    }

    public void showCardsAndScore(Dealer dealer, List<Participant> players) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format(CARD_AND_SCORE_FORMAT, dealer.getName(), dealer.showCards(), dealer.score().toString()))
                .append(NEW_LINE);

        for(Participant player : players) {
            sb.append(String.format(CARD_AND_SCORE_FORMAT, player.getName(), player.showCards(), player.score().toString()))
                    .append(NEW_LINE);
        }

        System.out.println(sb);
        System.out.println();
    }

    public void showResultProfit(Map<String, Double> resultMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("## 최종 수익").append(NEW_LINE);

        for(String name : resultMap.keySet()) {
            sb.append(String.format(RESULT_FORMAT, name, resultMap.get(name)))
                    .append(NEW_LINE);
        }

        System.out.println(sb);
    }

    public void showFirstTurnCards(Dealer dealer, List<Participant> players) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FIRST_TURN, dealer.getName(), dealer.showFirstCard()))
                .append(NEW_LINE);

        for(Participant player : players) {
            sb.append(String.format(FIRST_TURN, player.getName(), player.showCards()))
                    .append(NEW_LINE);
        }

        System.out.println(sb);
    }
}
