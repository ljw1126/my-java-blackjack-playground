package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Pattern.*;

public class Fixture {
    public static final Cards ACE_QUEEN_BLACKJACK = new Cards(new Card(HEART, ACE), new Card(CLOVER, QUEEN));
    public static final Cards ACE_KING_BLACKJACK = new Cards(new Card(HEART, ACE), new Card(CLOVER, KING));
    public static final Cards ACE_NINE_20 = new Cards(new Card(CLOVER, ACE), new Card(HEART, NINE));
    public static final Cards QUEEN_NINE_FIVE_25_BUST = new Cards(new Card(CLOVER, QUEEN), new Card(HEART, NINE), new Card(SPADE, FIVE));
    public static final Cards NINE_EIGHT_17 = new Cards(new Card(CLOVER, NINE), new Card(HEART, EIGHT));
    public static final Cards TEN_NINE_19 = new Cards(new Card(HEART, TEN), new Card(CLOVER, NINE));
    public static final Cards NINE_KING_19 = new Cards(new Card(CLOVER, NINE), new Card(DIAMOND, KING));
}
