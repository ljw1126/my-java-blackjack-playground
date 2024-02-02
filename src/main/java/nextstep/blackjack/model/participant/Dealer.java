package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;

public class Dealer extends AbstractParticipant{

    private static final String DEFAULT_NAME ="딜러";
    private static final int DRAWABLE_POINT = 16;

    public Dealer() {
        super(DEFAULT_NAME);
    }

    public boolean drawable() {
        return score().less(DRAWABLE_POINT);
    }

    public String showFirstCard() {
        Card card = this.cards.get(0);
        return card.show();
    }
}
