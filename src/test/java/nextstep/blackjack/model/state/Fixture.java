package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Pattern.*;

public class Fixture {
    public static final PlayingCard HEART_ACE = new PlayingCard(HEART, ACE);
    public static final PlayingCard DIAMOND_KING = new PlayingCard(DIAMOND, KING);
    public static final PlayingCard CLOVER_NINE = new PlayingCard(CLOVER, NINE);
    public static final PlayingCard SPACE_QUEEN = new PlayingCard(SPADE, QUEEN);
    public static final Cards CARDS_18 = new Cards(new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, SIX), new PlayingCard(HEART, TWO));

    public static final Cards CARDS_BLACKJACK_21 = new Cards(new PlayingCard(DIAMOND, ACE), new PlayingCard(SPADE, JACK));
    public static final Cards CARDS_BUST_22 = new Cards(new PlayingCard(DIAMOND, KING), new PlayingCard(SPADE, QUEEN), new PlayingCard(DIAMOND, TWO));
    public static final Cards CARDS_BUST_23 = new Cards(new PlayingCard(DIAMOND, EIGHT), new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, FIVE));
    public static final Cards CARDS_20 = new Cards(new PlayingCard(SPADE, QUEEN), new PlayingCard(HEART, KING));
    public static final Cards CARDS_20_KQ = new Cards(new PlayingCard(DIAMOND, KING), new PlayingCard(CLOVER, QUEEN));
}
