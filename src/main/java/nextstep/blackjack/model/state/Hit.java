package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public class Hit extends Running {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        cards.add(card);
        if(cards.isBust()) {
            return new Bust(cards);
        }

        return new Hit(cards);
    }

    @Override
    public State stay() {
        return new Stay(cards);
    }

}
