package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;

import java.util.Objects;

public abstract class Started implements State {
    protected final Cards cards;

    public Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Started stared = (Started) o;
        return Objects.equals(cards, stared.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }
}
