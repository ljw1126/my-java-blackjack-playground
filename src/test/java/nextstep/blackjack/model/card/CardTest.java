package nextstep.blackjack.model.card;

import org.junit.jupiter.api.Test;

import static nextstep.blackjack.model.card.Denomination.ACE;
import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    @Test
    void equals() {
        Card card = new Card(Pattern.CLOVER, ACE);
        assertThat(card).isEqualTo(new Card(Pattern.CLOVER, ACE));
    }

    @Test
    void getPoint() {
        Card card = new Card(Pattern.SPADE, ACE);
        assertThat(card.getPoint()).isEqualTo(ACE.getPoint());
    }
}