package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public class Dealer extends AbstractParticipant{

    public static final String DEFAULT_NAME ="딜러";
    private static final int DRAWABLE_POINT = 16;

    public Dealer() {
        super(DEFAULT_NAME);
    }
    public Dealer(Cards cards) {
        super(DEFAULT_NAME, cards);
    }

    public boolean drawable() {
        return score().less(DRAWABLE_POINT);
    }

    public String showFirstCard() {
        Card card = this.cards.get(0);
        return card.show();
    }
}
