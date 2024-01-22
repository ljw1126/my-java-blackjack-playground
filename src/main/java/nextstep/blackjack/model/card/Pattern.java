package nextstep.blackjack.model.card;

public enum Pattern {
    DIAMOND("♦"),
    HEART("♥"),
    CLOVER("♣"),
    SPADE("♠");

    private String shape;

    Pattern(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
