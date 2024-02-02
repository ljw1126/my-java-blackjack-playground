package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public class StateFactory {

    public static State create(Card one, Card two) {
        Cards cards = new Cards(one, two);
        if(cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }
}
