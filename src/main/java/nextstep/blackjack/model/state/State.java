package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public interface State {
    State draw(Card card);
    boolean isFinished();
    Cards cards();
    double profit(double betAmount);
    State stay();
}
