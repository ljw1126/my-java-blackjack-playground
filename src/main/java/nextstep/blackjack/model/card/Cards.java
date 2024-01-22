package nextstep.blackjack.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Cards {
    private static final int BLACKJACK_HANDS = 2;
    private final List<PlayingCard> playingCardList;

    public Cards(final PlayingCard... card) {
        this(Arrays.asList(card));
    }

    public Cards(final List<PlayingCard> cardList) {
        this.playingCardList = new ArrayList<>(cardList);
    }

    public void add(PlayingCard card) {
        playingCardList.add(card);
    }

    public List<PlayingCard> list() {
        return Collections.unmodifiableList(playingCardList);
    }

    public String joinPlayingCard() {
        return playingCardList.stream()
                .map(PlayingCard::toString)
                .collect(joining(", "));
    }

    public Score score() {
        Score score = sum();

        final long aceCount = getAceCount();
        for(int i = 0; i < aceCount; i++) {
            score = score.plusTenIfNotBust();
        }

        return score;
    }

    private long getAceCount() {
        return playingCardList.stream()
                .map(PlayingCard::getDenomination)
                .filter(Denomination::isAce)
                .count();
    }

    private int size() {
        return playingCardList.size();
    }

    public boolean isBlackjack() {
        return score().isBlackjackScore() && size() == BLACKJACK_HANDS;
    }

    public boolean isBust() {
        return score().isBust();
    }

    private Score sum() {
        return new Score(playingCardList.stream()
                .mapToInt(PlayingCard::getPoint)
                .sum()
        );
    }
}
