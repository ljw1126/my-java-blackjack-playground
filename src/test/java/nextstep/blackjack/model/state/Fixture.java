package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.PlayingCard;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Denomination.QUEEN;
import static nextstep.blackjack.model.card.Pattern.*;
import static nextstep.blackjack.model.card.Pattern.SPADE;

public class Fixture {
    public static final PlayingCard HEART_ACE = new PlayingCard(HEART, ACE);
    public static final PlayingCard DIAMOND_KING = new PlayingCard(DIAMOND, KING);
    public static final PlayingCard CLOVER_NINE = new PlayingCard(CLOVER, NINE);
    public static final PlayingCard SPACE_QUEEN = new PlayingCard(SPADE, QUEEN);


}
