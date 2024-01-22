package nextstep.blackjack.model;

import nextstep.blackjack.model.card.PlayingCard;
import nextstep.blackjack.model.participant.BetAmount;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.GameResult;
import nextstep.blackjack.model.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    
    private static final List<PlayingCard> DIAMOND_BLACKJACK = Arrays.asList(new PlayingCard(DIAMOND, ACE), new PlayingCard(DIAMOND, KING));
    private static final List<PlayingCard> HEART_BLACKJACK = Arrays.asList(new PlayingCard(HEART, ACE), new PlayingCard(HEART, QUEEN));
    private static final List<PlayingCard> CLOVER_BLACKJACK = Arrays.asList(new PlayingCard(CLOVER, ACE), new PlayingCard(CLOVER, JACK));
    private static final List<PlayingCard> SPADE_BLACKJACK = Arrays.asList(new PlayingCard(SPADE, ACE), new PlayingCard(SPADE, KING));
    
    private static final List<PlayingCard> CARD_17 = Arrays.asList(new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, SEVEN));
    private static final List<PlayingCard> CARD_19 = Arrays.asList(new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, NINE));
    private static final List<PlayingCard> CARD_21 = Arrays.asList(new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, FIVE), new PlayingCard(SPADE, SIX));
    private static final List<PlayingCard> CARD_22 = Arrays.asList(new PlayingCard(DIAMOND, KING), new PlayingCard(HEART, QUEEN), new PlayingCard(CLOVER, TWO));


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
