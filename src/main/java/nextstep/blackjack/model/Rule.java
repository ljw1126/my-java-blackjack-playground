package nextstep.blackjack.model;

import java.util.List;

public class Rule {
    private Dealer dealer;
    private List<Player> playerList;


    public Rule(Dealer dealer, List<Player> playerList) {
        this.dealer = dealer;
        this.playerList = playerList;
    }

    public void showPlayCardAndPoint() {
        StringBuilder sb = new StringBuilder();
        appendResult(sb, dealer);

        for (Participant player : playerList) {
           appendResult(sb, player);
        }

        System.out.println(sb);
    }

    private void appendResult(StringBuilder sb, Participant participant) {
        CardWrapper cards = participant.getCards();
        sb.append(participant.getName())
                .append(" : ")
                .append(cards.joinPlayingCard())
                .append(" -결과:")
                .append(cards.sumPoints())
                .append("\n");
    }

    public void showFinalProfit() {
        System.out.println("## 최종수익");
        StringBuilder sb = new StringBuilder();
    }
}
