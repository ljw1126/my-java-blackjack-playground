package nextstep.blackjack.model.card;

import java.util.Objects;

public class Card {
    private Pattern pattern;
    private Denomination denomination;

    public Card(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public boolean isAce() {
        return this.denomination.isAce();
    }

    public String show() {
        return this.pattern.getShape() + this.denomination.getText();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return pattern == card.pattern && denomination == card.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, denomination);
    }
}
