package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Player;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    @Test
    void dealerWin_딜러만_블랙잭() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, ACE)); // blackjack
        dealer.draw(new Card(CLOVER, QUEEN));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, ACE)); // 20
        player.draw(new Card(HEART, NINE));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 1000.0)
                .containsEntry("플레이어", -1000.0);
    }

    @Test
    void dealerWin_플레이어만_버스트() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, ACE)); // blackjack
        dealer.draw(new Card(CLOVER, QUEEN));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, QUEEN)); // 25
        player.draw(new Card(HEART, NINE));
        player.draw(new Card(SPADE, FIVE));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 1000.0)
                .containsEntry("플레이어", -1000.0);
    }

    @Test
    void dealerWin_딜러의_점수가_더높은경우() {
        Dealer dealer = new Dealer(); // 19
        dealer.draw(new Card(HEART, TEN));
        dealer.draw(new Card(CLOVER, NINE));

        Player player = new Player("플레이어"); // 17
        player.draw(new Card(CLOVER, NINE));
        player.draw(new Card(HEART, EIGHT));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 1000.0)
                .containsEntry("플레이어", -1000.0);
    }

    @Test
    void playerWin_플레이어만_블랙잭인_경우() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, ACE)); // 20
        dealer.draw(new Card(CLOVER, NINE));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, ACE)); // blackjack
        player.draw(new Card(HEART, KING));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, -1500.0)
                .containsEntry("플레이어", 1500.0);
    }

    @Test
    void playerWin_딜러만_버스트인_경우() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, SIX)); // 25
        dealer.draw(new Card(CLOVER, NINE));
        dealer.draw(new Card(DIAMOND, KING));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, ACE)); // 19
        player.draw(new Card(HEART, NINE));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, -1000.0)
                .containsEntry("플레이어", 1000.0);
    }

    @Test
    void playerWin_플레이어_점수가_더높은경우() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(CLOVER, NINE)); // 19
        dealer.draw(new Card(DIAMOND, KING));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, JACK)); // 20
        player.draw(new Card(DIAMOND, KING));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, -1000.0)
                .containsEntry("플레이어", 1000.0);
    }

    @Test
    void draw_둘다_블랙잭인경우() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, ACE)); // blackjack
        dealer.draw(new Card(CLOVER, QUEEN));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, ACE)); // blackjack
        player.draw(new Card(HEART, KING));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();

        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 0.0)
                .containsEntry("플레이어", 0.0);
    }

    @Test
    void draw_점수가같은경우() {
        Dealer dealer = new Dealer();
        dealer.draw(new Card(HEART, NINE)); // 19
        dealer.draw(new Card(CLOVER, QUEEN));

        Player player = new Player("플레이어");
        player.draw(new Card(CLOVER, NINE)); // 19
        player.draw(new Card(HEART, KING));
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();

        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 0.0)
                .containsEntry("플레이어", 0.0);
    }


}