package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;

import java.util.List;

public interface Participant {
    String getName();
    void firstCalling();
    void calling();
    void receiveCard(PlayingCard card);
    void receiveCards(List<PlayingCard> cards);
    Cards getCards();
    boolean isBust();
    boolean isBlackjack();
}
