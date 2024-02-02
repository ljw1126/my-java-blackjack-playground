package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Player;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import static nextstep.blackjack.model.rule.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    public static Stream<Arguments> dealerWinDataProvider() {
        return Stream.of(
            Arguments.arguments("딜러만 블랙잭인 경우", ACE_QUEEN_BLACKJACK, ACE_NINE_20),
            Arguments.arguments("플레이어가 버스트인 경우", ACE_NINE_20, QUEEN_NINE_FIVE_25_BUST),
            Arguments.arguments("둘 다 버스트 아니고, 딜러 점수가 높은 경우", TEN_NINE_19, NINE_EIGHT_17)
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("dealerWinDataProvider")
    void dealerWin(String title, Cards dealerCards, Cards playerCards) {
        Dealer dealer = new Dealer(dealerCards);

        Player player = new Player("플레이어", playerCards);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 1000.0)
                .containsEntry("플레이어", -1000.0);
    }


    public static Stream<Arguments> playerWinDataProvider() {
        return Stream.of(
                Arguments.arguments("플레이어만 블랙잭인 경우", ACE_NINE_20, ACE_QUEEN_BLACKJACK, -1500.0, 1500.0),
                Arguments.arguments("딜러가 버스트인 경우", QUEEN_NINE_FIVE_25_BUST, TEN_NINE_19, -1000.0, 1000.0),
                Arguments.arguments("둘 다 버스트 아니고, 플레이어 점수가 더 높은 경우",NINE_EIGHT_17, TEN_NINE_19, -1000.0, 1000.0)
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("playerWinDataProvider")
    void playerWin(String title, Cards dealerCards, Cards playerCards, double dealerProfit, double playerProfit) {
        Dealer dealer = new Dealer(dealerCards);

        Player player = new Player("플레이어", playerCards);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, dealerProfit)
                .containsEntry("플레이어", playerProfit);
    }

    public static Stream<Arguments> drawDataProvider() {
        return Stream.of(
                Arguments.arguments("둘 다 블랙잭인 경우", ACE_KING_BLACKJACK, ACE_QUEEN_BLACKJACK),
                Arguments.arguments("점수가 같은 경우", NINE_KING_19, TEN_NINE_19)
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("drawDataProvider")
    void draw(String title, Cards dealerCards, Cards playerCards) {
        Dealer dealer = new Dealer(dealerCards);

        Player player = new Player("플레이어", playerCards);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();

        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 0.0)
                .containsEntry("플레이어", 0.0);
    }
}