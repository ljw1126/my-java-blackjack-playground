package nextstep.blackjack.model.rule;

import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Player;
import nextstep.blackjack.model.state.Blackjack;
import nextstep.blackjack.model.state.Bust;
import nextstep.blackjack.model.state.State;
import nextstep.blackjack.model.state.Stay;
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
            Arguments.arguments("딜러만 블랙잭인 경우", new Blackjack(ACE_QUEEN_BLACKJACK), new Stay(ACE_NINE_20)),
            Arguments.arguments("플레이어가 버스트인 경우", new Stay(ACE_NINE_20), new Bust(QUEEN_NINE_FIVE_25_BUST)),
            Arguments.arguments("둘 다 버스트 아니고, 딜러 점수가 높은 경우", new Stay(TEN_NINE_19), new Stay(NINE_EIGHT_17))
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("dealerWinDataProvider")
    void dealerWin(String title, State dealerState, State plyaerState) {
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어");
        player.setState(plyaerState);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 1000.0)
                .containsEntry("플레이어", -1000.0);
    }


    public static Stream<Arguments> playerWinDataProvider() {
        return Stream.of(
                Arguments.arguments("플레이어만 블랙잭인 경우", new Stay(ACE_NINE_20), new Blackjack(ACE_QUEEN_BLACKJACK), -1500.0, 1500.0),
                Arguments.arguments("딜러가 버스트인 경우", new Bust(QUEEN_NINE_FIVE_25_BUST), new Stay(TEN_NINE_19), -1000.0, 1000.0),
                Arguments.arguments("둘 다 버스트 아니고, 플레이어 점수가 더 높은 경우", new Stay(NINE_EIGHT_17), new Stay(TEN_NINE_19), -1000.0, 1000.0)
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("playerWinDataProvider")
    void playerWin(String title, State dealerState, State plyaerState, double dealerProfit, double playerProfit) {
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어");
        player.setState(plyaerState);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();
        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, dealerProfit)
                .containsEntry("플레이어", playerProfit);
    }

    public static Stream<Arguments> drawDataProvider() {
        return Stream.of(
                Arguments.arguments("둘 다 블랙잭인 경우", new Blackjack(ACE_KING_BLACKJACK), new Blackjack(ACE_QUEEN_BLACKJACK)),
                Arguments.arguments("점수가 같은 경우", new Stay(NINE_KING_19), new Stay(TEN_NINE_19))
        );
    }

    @ParameterizedTest(name = "케이스 : {0}")
    @MethodSource("drawDataProvider")
    void draw(String title, State dealerState, State plyaerState) {
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어");
        player.setState(plyaerState);
        player.initBetAmount(1000);

        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Double> resultMap = rule.getResultMap();

        assertThat(resultMap)
                .containsEntry(Dealer.DEFAULT_NAME, 0.0)
                .containsEntry("플레이어", 0.0);
    }
}