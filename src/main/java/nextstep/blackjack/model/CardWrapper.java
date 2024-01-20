package nextstep.blackjack.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CardWrapper {
    private static final int MAX_POINT = 21;
    private static final int BLACKJACK_HANDS = 2;
    private List<Card> playingCard = new ArrayList<>();

    public CardWrapper() {
    }

    public static CardWrapper of(List<Card> cardList) {
        CardWrapper cards = new CardWrapper();
        cards.addAll(cardList);
        return cards;
    }

    private void addAll(List<Card> cardList) {
        playingCard.addAll(cardList);
    }

    public void add(Card card) {
        playingCard.add(card);
    }

    public Card get(int idx) {
        return playingCard.get(idx);
    }

    public String joinPlayingCard() {
        return playingCard.stream()
                .map(Card::toString)
                .collect(joining(", "));
    }

    public int sumPoints() {
        List<Card> sorted = soredPlayingCardByPointDesc();

        int points = 0;
        for(Card card : sorted) {
            points = card.sum(points);
        }

        return points;
    }

    private List<Card> soredPlayingCardByPointDesc() {
        return  playingCard.stream()
                .sorted(Comparator.comparingInt(Card::getPont).reversed())
                .collect(toList());
    }

    public boolean isBlackjack() {
        return sumPoints() == MAX_POINT && playingCard.size() == BLACKJACK_HANDS;
    }

    public boolean isBust() {
        return sumPoints() > MAX_POINT;
    }
}
