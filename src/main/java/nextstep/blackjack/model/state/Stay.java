package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Stay extends Finished {
    private static final double RATE = 1.0;
    public Stay(Cards cards) {
        super(cards);
    }

    public double profit(double betAmount) {
        return RATE * betAmount;
    }
}
