package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;

public class StateFactory {

    public static State create(PlayingCard first, PlayingCard second) {
        Cards cards = new Cards(first, second);
        if(cards.isBlackjack()) {
            return new Blackjack(cards);
        }

        return new Hit(cards);
    }
}
