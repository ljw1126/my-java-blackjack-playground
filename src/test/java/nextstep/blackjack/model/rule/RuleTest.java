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

import static nextstep.blackjack.model.state.Fixture.*;
import static org.assertj.core.api.Assertions.assertThat;

class RuleTest {

    @ParameterizedTest
    @MethodSource("playerWinCaseProvider")
    void player_win_case(State dealerState, State playerState, Map<String, Integer> expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어1");
        player.setState(playerState);
        player.initBetAmount(1000);

        // when
        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Integer> actual = rule.judgement();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> playerWinCaseProvider() {
        return Stream.of(
                Arguments.arguments(
                        new Bust(CARDS_BUST_23),
                        new Stay(CARDS_18),
                        Map.of("딜러", -1000, "플레이어1", 1000)
                ),
                Arguments.arguments(
                        new Stay(CARDS_18),
                        new Blackjack(CARDS_BLACKJACK_21),
                        Map.of("딜러", -1500, "플레이어1", 1500)
                ),
                Arguments.arguments(
                        new Stay(CARDS_18),
                        new Stay(CARDS_20),
                        Map.of("딜러", -1000, "플레이어1", 1000)
                )
        );
    }


    @ParameterizedTest
    @MethodSource("playerLoseCaseProvider")
    void player_loser_case(State dealerState, State playerState, Map<String, Integer> expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어1");
        player.setState(playerState);
        player.initBetAmount(1000);

        // when
        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Integer> actual = rule.judgement();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> playerLoseCaseProvider() {
        return Stream.of(
                Arguments.arguments(
                        new Bust(CARDS_BUST_23),
                        new Bust(CARDS_BUST_22),
                        Map.of("딜러", 1000, "플레이어1", -1000)
                ),
                Arguments.arguments(
                        new Stay(CARDS_20),
                        new Bust(CARDS_BUST_22),
                        Map.of("딜러", 1000, "플레이어1", -1000)
                ),
                Arguments.arguments(
                        new Blackjack(CARDS_BLACKJACK_21),
                        new Stay(CARDS_20),
                        Map.of("딜러", 1000, "플레이어1", -1000)
                ),
                Arguments.arguments(
                        new Stay(CARDS_20),
                        new Stay(CARDS_18),
                        Map.of("딜러", 1000, "플레이어1", -1000)
                )
        );
    }
    
    @ParameterizedTest
    @MethodSource("playerDrawCaseProvider")
    void player_draw(State dealerState, State playerState, Map<String, Integer> expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.setState(dealerState);

        Player player = new Player("플레이어1");
        player.setState(playerState);
        player.initBetAmount(1000);

        // when
        Rule rule = new Rule(dealer, Arrays.asList(player));
        Map<String, Integer> actual = rule.judgement();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    public static Stream<Arguments> playerDrawCaseProvider() {
        return Stream.of(
                Arguments.arguments(
                        new Stay(CARDS_20),
                        new Stay(CARDS_20_KQ),
                        Map.of("딜러", 0, "플레이어1", 0)
                )
        );
    }

}