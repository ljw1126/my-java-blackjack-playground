package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.state.State;

public interface Participant {
    String getName();
    Cards cards();
    int getBetAmount();
    void draw(Card card);
    void setState(State state);
    boolean isBlackjack();
    boolean isBust();
    Score score();
    String showCards();
    void initBetAmount(int betAmount);

    boolean isFinished();

    void stay();

    double profit();
}
