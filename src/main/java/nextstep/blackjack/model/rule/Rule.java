package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Rule {
    private Dealer dealer;
    private List<Participant> players;
    public Rule(Dealer dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Double> getResultMap() {
        Map<String, Double> resultMap = new LinkedHashMap<>();

        for(Participant player : this.players) {
            double profit = judgement(this.dealer, player);
            resultMap.put(this.dealer.getName(), resultMap.getOrDefault(this.dealer.getName(), 0.0) - (profit));
            resultMap.put(player.getName(), profit);
        }

        return resultMap;
    }

    private double judgement(Dealer dealer, Participant player) {
        if(isDealerWin(dealer, player)) {
            return negativeProfit(player);
        }

        if(isPlayerWin(dealer, player)) {
            return player.profit();
        }

        return 0.0;
    }

    private double negativeProfit(Participant player) {
        double profit = player.profit();
        if(profit > 0) {
            return -(profit);
        }

        return profit;
    }

    private boolean isDealerWin(Dealer dealer, Participant player) {
        return (dealer.isBlackjack() && !player.isBlackjack())
                || (!dealer.isBust() && player.isBust())
                || (!dealer.isBust() && !player.isBust() && dealer.score().greaterThan(player.score()));
    }

    private boolean isPlayerWin(Dealer dealer, Participant player) {
        return (!dealer.isBlackjack() && player.isBlackjack())
                || (dealer.isBust() && !player.isBust())
                || (!dealer.isBust() && !player.isBust() && player.score().greaterThan(dealer.score()));
    }


}
