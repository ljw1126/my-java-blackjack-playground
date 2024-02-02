package nextstep.blackjack.model.card;

import nextstep.blackjack.model.participant.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Cards {
    private static final int TWO = 2;
    private static final int NAX_POINT = 21;
    private List<Card> cardList = new ArrayList<>();

    public Cards() {
    }

    public void add(Card card) {
        this.cardList.add(card);
    }

    public boolean isBlackjack() {
        return cardList.size() == TWO && score().isSame(NAX_POINT);
    }

    public Score score() {
        int sum = sum();
        int aceCount = aceCount();

        Score score = new Score(sum);
        for(int i = 0; i < aceCount; i++) {
            score = score.plusTenIfNotBust();
        }

        return score;
    }

    private int sum() {
        return this.cardList.stream()
                .map(Card::getPoint)
                .reduce(0, Integer::sum);
    }

    private int aceCount() {
        return (int) this.cardList.stream()
                .map(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return score().over(NAX_POINT);
    }

    public String joinCardList() {
        return this.cardList.stream()
                .map(Card::show)
                .collect(joining(", "));
    }

    public Card get(int i) {
        return this.cardList.get(i);
    }
}
