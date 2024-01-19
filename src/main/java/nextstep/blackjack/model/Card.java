package nextstep.blackjack.model;

public class Card {
    private String pattern;
    private String value;

    public Card(String pattern, String value) {
        this.pattern = pattern;
        this.value = value;
    }

    public String getPattern() {
        return pattern;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return pattern + value;
    }
}
