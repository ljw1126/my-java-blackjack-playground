package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Stay extends Finished {
    private static final double rate = 1.0;
    public Stay(Cards cards) {
        super(cards);
    }

    public double profit(double betAmount) {
        return rate * betAmount;
    }
}
