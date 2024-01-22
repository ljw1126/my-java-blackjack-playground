package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public abstract class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(double money) {
        throw new UnsupportedOperationException();
    }
}
