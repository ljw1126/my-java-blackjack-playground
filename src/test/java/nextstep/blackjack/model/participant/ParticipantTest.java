package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Denomination;
import nextstep.blackjack.model.card.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParticipantTest {

    @Test
    void isBlackjack() {
        Participant player = new Player("플레이어1");
        player.draw(new Card(Pattern.SPADE, Denomination.ACE));
        player.draw(new Card(Pattern.HEART, Denomination.KING));

        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    void isBust() {
        Participant player = new Player("플레이어1");
        player.draw(new Card(Pattern.SPADE, Denomination.KING));
        player.draw(new Card(Pattern.HEART, Denomination.QUEEN));
        player.draw(new Card(Pattern.HEART, Denomination.TWO));

        assertThat(player.isBust()).isTrue();
    }

    @Test
    void score() {
        Participant player = new Player("플레이어1");
        player.draw(new Card(Pattern.SPADE, Denomination.KING));
        player.draw(new Card(Pattern.HEART, Denomination.QUEEN));

        Score score = player.score();
        assertThat(score).isEqualTo(new Score(20));
    }

}