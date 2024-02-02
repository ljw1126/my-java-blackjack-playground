package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }
}
