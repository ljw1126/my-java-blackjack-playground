package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Rule {
    private final Dealer dealer;
    private final List<Player> playerList;
    private final List<Participant> participants;

    public Rule(Dealer dealer, List<Player> playerList) {
        this.dealer = dealer;
        this.playerList = playerList;

        this.participants = new LinkedList<>();
        this.participants.add(dealer);
        this.participants.addAll(playerList);
    }

    public List<Participant> getParticipant() {
        return participants;
    }

    public Map<String, Integer> judgement() {
        Map<String, Integer> resultMap = new LinkedHashMap<>();
        for(Player player : playerList) {
            int profit = (int) compare(dealer, player);
            resultMap.put(dealer.getName(), resultMap.getOrDefault(dealer.getName(), 0) + Math.negateExact(profit));
            resultMap.put(player.getName(), profit);
        }

        return resultMap;
    }

    public double compare(Dealer dealer, Player player) {
        if(isPlayerWin(dealer, player)) {
            return player.profit();
        }

        if(isPlayerLose(dealer, player)) {
            double profit = player.profit();
            return profit < 0 ? profit : -profit; // TODO
        }

        return 0.0;
    }

    public boolean isPlayerWin(Dealer dealer, Player player) {
        return (dealer.isBust() && !player.isBust())
                || (!dealer.isBlackjack() && player.isBlackjack())
                || (!dealer.isBust() && !player.isBust() && player.score().greaterThan(dealer.score()));
    }

    public boolean isPlayerLose(Dealer dealer, Player player) {
        return (dealer.isBust() && player.isBust())
                || (!dealer.isBust() && player.isBust())
                || (dealer.isBlackjack() && !player.isBlackjack())
                || (!dealer.isBust() && !player.isBust() && dealer.score().greaterThan(player.score()));
    }

}
