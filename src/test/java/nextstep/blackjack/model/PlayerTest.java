package nextstep.blackjack.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nextstep.blackjack.model.Denomination.*;
import static nextstep.blackjack.model.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    
    private static final List<Card> DIAMOND_BLACKJACK = Arrays.asList(new Card(DIAMOND, ACE), new Card(DIAMOND, KING));
    private static final List<Card> HEART_BLACKJACK = Arrays.asList(new Card(HEART, ACE), new Card(HEART, QUEEN));
    private static final List<Card> CLOVER_BLACKJACK = Arrays.asList(new Card(CLOVER, ACE), new Card(CLOVER, JACK));
    private static final List<Card> SPADE_BLACKJACK = Arrays.asList(new Card(SPADE, ACE), new Card(SPADE, KING));
    
    private static final List<Card> CARD_17 = Arrays.asList(new Card(DIAMOND, KING), new Card(HEART, SEVEN));
    private static final List<Card> CARD_19 = Arrays.asList(new Card(DIAMOND, KING), new Card(HEART, NINE));
    private static final List<Card> CARD_21 = Arrays.asList(new Card(DIAMOND, KING), new Card(HEART, FIVE), new Card(SPADE, SIX));
    private static final List<Card> CARD_22 = Arrays.asList(new Card(DIAMOND, KING), new Card(HEART, QUEEN), new Card(CLOVER, TWO));


    @Test
    void player_win() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_17);

        Player player = new Player("플레이어1");
        player.receiveCards(CARD_19);

        GameResult result = player.judgement(dealer);

        // then
        assertThat(result).isEqualTo(GameResult.WIN);
    }

    @Test
    void player_blackjack() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_21);

        Player player = new Player("플레이어1");
        player.receiveCards(DIAMOND_BLACKJACK);

        GameResult result = player.judgement(dealer);

        // then
        assertThat(result).isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    void draw_딜러와플레이어가블랙잭일때() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(HEART_BLACKJACK);

        Player player = new Player("플레이어1");
        player.receiveCards(DIAMOND_BLACKJACK);

        GameResult result = player.judgement(dealer);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    void lose_플레이어가_bust일때() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_21);

        Player player = new Player("플레이어1");
        player.receiveCards(CARD_22);

        GameResult result = player.judgement(dealer);

        // then
        assertThat(result).isEqualTo(GameResult.LOSE);
    }

    @Test
    void player_draw() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_19);

        Player player = new Player("플레이어1");
        player.receiveCards(CARD_19);

        GameResult result = player.judgement(dealer);

        // then
        assertThat(result).isEqualTo(GameResult.DRAW);
    }

    @Test
    void revenue_플레이어가_이기는경우() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_17);

        Player player = new Player("플레이어1");
        player.receiveCards(CARD_19);
        player.initBetAmount(1000.0);

        BetAmount revenue = player.revenue(dealer);

        // then
        assertThat(revenue).isEqualTo(BetAmount.of(1000.0 * GameResult.WIN.getEarningRate()));
    }

    @Test
    void revenue_플레이어가블랙잭으로이기는경우() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(CARD_17);

        Player player = new Player("플레이어1");
        player.receiveCards(SPADE_BLACKJACK);
        player.initBetAmount(1000.0);

        BetAmount revenue = player.revenue(dealer);

        // then
        assertThat(revenue).isEqualTo(BetAmount.of(1000.0 * GameResult.BLACKJACK.getEarningRate()));
    }

    @Test
    void revenue_플레이어가지는경우() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(HEART_BLACKJACK);

        Player player = new Player("플레이어1");
        player.receiveCards(CARD_19);
        player.initBetAmount(1000.0);

        BetAmount revenue = player.revenue(dealer);

        // then
        assertThat(revenue).isEqualTo(BetAmount.of(1000.0 * GameResult.LOSE.getEarningRate()));
    }

    @Test
    void revenue_무승부인경우() {
        // given
        // when
        Dealer dealer = new Dealer();
        dealer.receiveCards(DIAMOND_BLACKJACK);

        Player player = new Player("플레이어1");
        player.receiveCards(CLOVER_BLACKJACK);
        player.initBetAmount(1000.0);

        BetAmount revenue = player.revenue(dealer);

        // then
        assertThat(revenue).isEqualTo(BetAmount.of(1000.0 * GameResult.DRAW.getEarningRate()));
    }
}
