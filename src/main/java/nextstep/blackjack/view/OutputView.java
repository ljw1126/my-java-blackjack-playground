package nextstep.blackjack.view;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    private static final String NAME_DELIMITER = ",";
    public static void showFirstCards(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getName();
        String playerNames = players.stream().map(Participant::getName).collect(joining(NAME_DELIMITER));
        System.out.println(String.format("%s와 %s 에게 패를 2장씩 나누었습니다", dealerName, playerNames));

        System.out.println(String.format("%s 카드 : %s", dealerName, dealer.getFirstPlayingCard()));
        for(Player player : players) {
            showCards(player);
        }

        System.out.println();
    }

    public static void showCards(Participant participant) {
        StringBuilder sb = new StringBuilder();
        sb.append(participant.getName()).append("카드 : ").append(participant.joiningPlayingCard());
        System.out.println(sb);
    }

    public static void showPlayingCardAndScore(List<Participant> participants) {
        StringBuilder sb = new StringBuilder();

        for (Participant player : participants) {
            appendResult(sb, player);
        }

        System.out.println(sb);
    }
    private static void appendResult(StringBuilder sb, Participant participant) {
        Cards cards = participant.cards();
        sb.append(participant.getName())
                .append(" : ")
                .append(cards.joinPlayingCard())
                .append(" - 결과:")
                .append(cards.score())
                .append("\n");
    }

    public static void showResultProfit(Map<String, Integer> resultMap) {
        StringBuilder sb = new StringBuilder();
        for(String name : resultMap.keySet()) {
            sb.append(name).append(" : ").append(resultMap.get(name)).append("\n");
        }

        System.out.println(sb);
    }

}
