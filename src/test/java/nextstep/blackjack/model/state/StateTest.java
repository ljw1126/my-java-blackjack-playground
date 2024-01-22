package nextstep.blackjack.model.state;

import nextstep.blackjack.model.card.Cards;
import org.junit.jupiter.api.Test;

import static nextstep.blackjack.model.state.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateTest {

    @Test
    void stateHit() {
        State state = StateFactory.create(CLOVER_NINE, SPACE_QUEEN);
        State nextState = state.draw(HEART_ACE);

        assertThat(nextState).isInstanceOf(Hit.class);
    }

    @Test
    void stateBust() {
        State state = StateFactory.create(CLOVER_NINE, SPACE_QUEEN);
        State nextState = state.draw(DIAMOND_KING);

        assertThat(nextState).isInstanceOf(Bust.class);
    }

    @Test
    void stateStay() {
        State state = StateFactory.create(CLOVER_NINE, SPACE_QUEEN);
        State nextState = state.draw(HEART_ACE);
        State actual = nextState.stay();

        assertThat(actual).isInstanceOf(Stay.class);
        assertThat(actual).isEqualTo(new Stay(new Cards(CLOVER_NINE, SPACE_QUEEN, HEART_ACE)));
    }

    @Test
    void blackjack상태일때() {
        State state = StateFactory.create(HEART_ACE, SPACE_QUEEN);

        assertThat(state.cards()).isEqualTo(new Cards(HEART_ACE, SPACE_QUEEN));

        assertThat(state.isFinished()).isTrue();

        assertThatThrownBy(() -> state.draw(null))
                .isInstanceOf(UnsupportedOperationException.class);

        assertThatThrownBy(() -> state.stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }
}