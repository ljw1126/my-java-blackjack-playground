package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.participant.BetAmount;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;
import nextstep.blackjack.model.card.Cards;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Rule {
    private Dealer dealer;
    private List<Player> playerList;
    private List<Participant> participants = new LinkedList<>();

    public Rule(Dealer dealer, List<Player> playerList) {
        this.dealer = dealer;
        this.playerList = playerList;

        this.participants.add(dealer);
        this.participants.addAll(playerList);
    }

    public void showPlayCardAndPoint() {
        StringBuilder sb = new StringBuilder();

        for (Participant player : participants) {
            appendResult(sb, player);
        }

        System.out.println(sb);
    }

    private void appendResult(StringBuilder sb, Participant participant) {
        Cards cards = participant.getCards();
        sb.append(participant.getName())
                .append(" : ")
                .append(cards.joinPlayingCard())
                .append(" -결과:")
                .append(cards.score())
                .append("\n");
    }

    public void showRevenue(Map<String, BetAmount> betAmountMap) {
        System.out.println("## 최종수익");
        StringBuilder sb = new StringBuilder();

        for(String name : betAmountMap.keySet()) {
            sb.append(name).append(" : ").append(betAmountMap.get(name)).append("\n");
        }

        System.out.println(sb);
    }

    public Map<String, BetAmount> getNameBetAmountResultMap() {
        Map<String, BetAmount> betAmountMap = new LinkedHashMap<>();
        // 딜러 수익
        betAmountMap.put(dealer.getName(), dealer.calculateRevenue(playerList));

        // 유저 수익
        for (Player player : playerList) {
            betAmountMap.put(player.getName(), player.revenue(dealer));
        }
        return betAmountMap;
    }
}
