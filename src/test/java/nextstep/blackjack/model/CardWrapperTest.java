package nextstep.blackjack.model;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static nextstep.blackjack.model.card.Denomination.*;
import static nextstep.blackjack.model.card.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardWrapperTest {

    @ParameterizedTest
    @MethodSource("cardListAndSumPointProvider")
    void sumPoints(List<PlayingCard> cardList, int expected) {
        // given
        // when
        Cards cards = Cards.of(cardList);
        int actual = cards.sum();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> cardListAndSumPointProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, EIGHT), new PlayingCard(CLOVER, ACE)), 19),
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, ACE), new PlayingCard(CLOVER, ACE)), 12),
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, KING), new PlayingCard(CLOVER, ACE)), 21),
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, ACE), new PlayingCard(CLOVER, TWO), new PlayingCard(HEART, KING)), 13)
        );
    }


    @DisplayName("카드 합이 21이고, 패가 두 장인 경우 blackjack이다")
    @ParameterizedTest
    @MethodSource("blackjackPlayingCardProvider")
    void blackjack(List<PlayingCard> cardList, int point, boolean expected) {
        // given
        // when
        Cards cards = Cards.of(cardList);

        // then
        assertThat(cards.sum()).isEqualTo(point);
        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    static Stream<Arguments> blackjackPlayingCardProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new PlayingCard(HEART, ACE), new PlayingCard(CLOVER, KING)), 21, true),
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, JACK), new PlayingCard(CLOVER, ACE)), 21, true),
                Arguments.arguments(Arrays.asList(new PlayingCard(HEART, ACE), new PlayingCard(CLOVER, KING), new PlayingCard(DIAMOND, QUEEN)), 21, false)
        );
    }

    @DisplayName("카드 포인트 합이 21을 초과하면 bust이다")
    @ParameterizedTest
    @MethodSource("bustPlayingCardProvider")
    void bust(List<PlayingCard> cardList, int point, boolean expected) {
        // given
        // when
        Cards cards = Cards.of(cardList);

        // then
        assertThat(cards.sum()).isEqualTo(point);
        assertThat(cards.isBust()).isEqualTo(expected);
    }

    static Stream<Arguments> bustPlayingCardProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new PlayingCard(HEART, TWO), new PlayingCard(CLOVER, KING), new PlayingCard(DIAMOND, JACK)), 22, true),
                Arguments.arguments(Arrays.asList(new PlayingCard(HEART, ACE), new PlayingCard(CLOVER, KING), new PlayingCard(DIAMOND, THREE), new PlayingCard(SPADE, ACE)), 15, false),
                Arguments.arguments(Arrays.asList(new PlayingCard(DIAMOND, JACK), new PlayingCard(CLOVER, ACE)), 21, false)
        );
    }
}