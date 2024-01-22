package nextstep.blackjack.model.card;

public class PlayingCard {
    private Pattern pattern;
    private Denomination denomination;

    public PlayingCard(Pattern pattern, Denomination denomination) {
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public int getPoint() {
        return denomination.getPoint();
    }

    public boolean isAce() {
        return denomination.isAce();
    }

    @Override
    public String toString() {
        return pattern.getShape() + " " + denomination.getText();
    }
}
