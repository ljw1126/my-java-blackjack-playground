package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public interface Participant {
    String getName();
    Cards getCards();
    int getBetAmount();
    void draw(Card card);
    boolean isBlackjack();
    boolean isBust();
    Score score();
    String showCards();
    void initBetAmount(int betAmount);
}
