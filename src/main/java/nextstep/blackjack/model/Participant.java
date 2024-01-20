package nextstep.blackjack.model;

import java.util.List;

public interface Participant {
    String getName();
    void firstCalling();
    void calling();
    void receiveCard(Card card);
    void receiveCards(List<Card> cards);
    CardWrapper getCards();
    boolean isBust();
    boolean isBlackjack();
}
