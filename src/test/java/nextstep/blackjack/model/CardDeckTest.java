package nextstep.blackjack.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CardDeckTest {

    @Test
    void size() {
        CardDeck cardDeck = new CardDeck();
        Assertions.assertThat(cardDeck.size()).isEqualTo(52);
    }
}