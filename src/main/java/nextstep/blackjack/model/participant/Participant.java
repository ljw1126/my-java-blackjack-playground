package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;
import nextstep.blackjack.model.card.Score;

public interface Participant {
    String getName();
    void receivePlayingCard(PlayingCard card);
    Cards cards();
    boolean isBust();
    boolean isBlackjack();
    String joiningPlayingCard();

    Score score();

    double profit();

    void stay();

    boolean isFinished();
}
