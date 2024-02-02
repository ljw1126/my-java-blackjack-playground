package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;

public class Player extends AbstractParticipant {

    public Player(String name) {
        this(name, new Cards());
    }

    public Player(String name, Cards cards) {
        super(name, cards);
    }
}
