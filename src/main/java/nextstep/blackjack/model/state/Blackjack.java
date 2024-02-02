package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Blackjack extends Finished{
    private static final double RATE = 1.5;

    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount) {
        return RATE * betAmount;
    }
}
