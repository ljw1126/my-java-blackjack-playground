package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Denomination;
import nextstep.blackjack.model.card.Pattern;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StateFactoryTest {

    @Test
    void createBlackjack() {
        State state = StateFactory.create(new Card(Pattern.CLOVER, Denomination.ACE), new Card(Pattern.SPADE, Denomination.KING));
        assertThat(state).isInstanceOf(Blackjack.class);
    }

    @Test
    void createHit() {
        State state = StateFactory.create(new Card(Pattern.CLOVER, Denomination.ACE), new Card(Pattern.SPADE, Denomination.NINE));
        assertThat(state).isInstanceOf(Hit.class);
    }
}