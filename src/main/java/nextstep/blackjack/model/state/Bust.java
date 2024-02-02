package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Bust extends Finished {
    private static final double rate = -1.0;

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount) {
        return rate * betAmount;
    }
}
