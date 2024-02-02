package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Blackjack extends Finished{
    private static final double rate = 1.5;

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount) {
        return rate * betAmount;
    }
}
