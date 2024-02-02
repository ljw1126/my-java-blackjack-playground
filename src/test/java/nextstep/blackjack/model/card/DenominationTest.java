package nextstep.blackjack.model.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DenominationTest {


    @Test
    void isAce() {
        Denomination denomination = Denomination.ACE;
        assertThat(denomination.isAce()).isTrue();
    }

}