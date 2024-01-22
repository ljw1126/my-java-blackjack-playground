package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.PlayingCard;
import nextstep.blackjack.model.card.Score;

public class Dealer extends AbstractParticipant {
    private static final int POINT_BOUNDARY = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean drawable() {
        Score score = cards().score();
        return score.getScore() <= POINT_BOUNDARY;
    }

    public PlayingCard getFirstPlayingCard() {
        return cards().get(0);
    }
}
