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

    public int getPont() {
        return denomination.getPoint();
    }

    public int sum(int points) {
        return denomination.sum(points);
    }

    @Override
    public String toString() {
        return pattern.getShape() + " " + denomination.getText();
    }
}
