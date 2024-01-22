package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;

public interface State {
    State draw(PlayingCard playingCard);
    boolean isFinished();
    double profit(double money);
    State stay();
    Cards cards();
}
