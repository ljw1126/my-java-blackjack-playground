package nextstep.blackjack.model.card;

//Denomination : 끗수
public enum Denomination {
    ACE(1, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J");

    private int point;
    private String text;

    Denomination(int point, String text) {
        this.point = point;
        this.text = text;
    }

    public int getPoint() {
        return point;
    }

    public String getText() {
        return text;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public int sum(int points) {
        int sum = points;
        if(this.isAce()) {
            sum += getAcePointBy(points);
            return sum;
        }

        sum += getPoint();
        return sum;
    }

    private int getAcePointBy(int points) {
        if(points + 11 <= 21) {
            return 11;
        }

        return 1;
    }
}
