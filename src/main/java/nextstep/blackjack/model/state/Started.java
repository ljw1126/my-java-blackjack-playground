package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

public abstract class Started implements State {
    protected Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return this.cards;
    }
}
