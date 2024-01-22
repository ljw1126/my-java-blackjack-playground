package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(PlayingCard card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    public double profit(double money) {
        return money * earningRate();
    }

    abstract double earningRate();
}
