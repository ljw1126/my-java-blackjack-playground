package nextstep.blackjack.model.card;

import java.util.*;

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
        for (int i = 0; i < aceCount; i++) {
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

    public PlayingCard get(int idx) {
        return playingCardList.get(idx);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return Objects.equals(playingCardList, cards.playingCardList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playingCardList);
    }
}
