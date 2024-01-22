package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public class Stay extends Finished {
    private static final double EARNING_RATE = 1.0;

    public Stay(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return EARNING_RATE;
    }
}
