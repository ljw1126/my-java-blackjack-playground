package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Bust extends Finished {
    private static final double RATE = -1.0;

    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double betAmount) {
        return RATE * betAmount;
    }
}
