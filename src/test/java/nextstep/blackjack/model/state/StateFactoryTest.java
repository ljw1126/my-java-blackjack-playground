package nextstep.blackjack.model.state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.blackjack.model.state.Fixture.*;

class StateFactoryTest {
    @Test
    void create() {
        State blackjack = StateFactory.create(HEART_ACE, DIAMOND_KING);
        State card19 = StateFactory.create(CLOVER_NINE, SPACE_QUEEN);

        Assertions.assertThat(blackjack).isInstanceOf(Blackjack.class);
        Assertions.assertThat(card19).isInstanceOf(Hit.class);
    }
}