package nextstep.blackjack.model;

import nextstep.blackjack.model.card.CardDeck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDeckTest {

    private int DECK_MAX_SIZE = 52;

    @Test
    void size() {
        CardDeck cardDeck = new CardDeck();
        assertThat(cardDeck.size()).isEqualTo(DECK_MAX_SIZE);
    }

    @Test
    void draw() {
        CardDeck cardDeck = new CardDeck();
        cardDeck.draw();

        assertThat(cardDeck.size()).isEqualTo(DECK_MAX_SIZE - 1);
    }
}