package nextstep.blackjack.model.card;

public enum Pattern {

    DIAMOND("♦"),
    CLOVER("♣"),
    SPADE("♠"),
    HEART("♥");

    private String shape;

    Pattern(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }
}
