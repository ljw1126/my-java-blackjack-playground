package nextstep.blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {

    @Test
    void init_사이즈는_52이다() {
        Deck deck = new Deck();
        assertThat(deck.size()).isEqualTo(Deck.INIT_SIZE);
    }

    @Test
    void 카드_드로우했을때_사이즈는_한장이_줄어든다() {
       Deck deck = new Deck();
       deck.draw();

       assertThat(deck.size()).isEqualTo(deck.INIT_SIZE - 1);
    }
}