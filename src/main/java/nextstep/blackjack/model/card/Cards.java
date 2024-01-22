package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Cards {
    private static final int MAX_POINT = 21;
    private static final int BLACKJACK_HANDS = 2;
    private List<PlayingCard> playingCard = new ArrayList<>();

    public Cards() {}

    public static Cards of(List<PlayingCard> cardList) {
        Cards cards = new Cards();
        cards.addAll(cardList);
        return cards;
    }

    public void addAll(List<PlayingCard> cardList) {
        playingCard.addAll(cardList);
    }

    public void add(PlayingCard card) {
        playingCard.add(card);
    }

    public PlayingCard get(int idx) {
        return playingCard.get(idx);
    }

    public String joinPlayingCard() {
        return playingCard.stream()
                .map(PlayingCard::toString)
                .collect(joining(", "));
    }

    public int sum() {
        List<PlayingCard> sorted = soredPlayingCardByPointDesc();

        int points = 0;
        for(PlayingCard card : sorted) {
            points = card.sum(points);
        }

        return points;
    }

    private List<PlayingCard> soredPlayingCardByPointDesc() {
        return  playingCard.stream()
                .sorted(Comparator.comparingInt(PlayingCard::getPont).reversed())
                .collect(toList());
    }

    public boolean isBlackjack() {
        return sum() == MAX_POINT && playingCard.size() == BLACKJACK_HANDS;
    }

    public boolean isBust() {
        return sum() > MAX_POINT;
    }
}
