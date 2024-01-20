package nextstep.blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static nextstep.blackjack.model.Denomination.*;
import static nextstep.blackjack.model.Pattern.*;
import static org.assertj.core.api.Assertions.assertThat;

class CardWrapperTest {

    @ParameterizedTest
    @MethodSource("cardListAndSumPointProvider")
    void sumPoints(List<Card> cardList, int expected) {
        // given
        // when
        CardWrapper cards = CardWrapper.of(cardList);
        int actual = cards.sumPoints();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> cardListAndSumPointProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, EIGHT), new Card(CLOVER, ACE)), 19),
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, ACE), new Card(CLOVER, ACE)), 12),
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, KING), new Card(CLOVER, ACE)), 21),
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, ACE), new Card(CLOVER, TWO), new Card(HEART, KING)), 13)
        );
    }


    @DisplayName("카드 합이 21이고, 패가 두 장인 경우 blackjack이다")
    @ParameterizedTest
    @MethodSource("blackjackPlayingCardProvider")
    void blackjack(List<Card> cardList, int point, boolean expected) {
        // given
        // when
        CardWrapper cards = CardWrapper.of(cardList);

        // then
        assertThat(cards.sumPoints()).isEqualTo(point);
        assertThat(cards.isBlackjack()).isEqualTo(expected);
    }

    static Stream<Arguments> blackjackPlayingCardProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new Card(HEART, ACE), new Card(CLOVER, KING)), 21, true),
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, JACK), new Card(CLOVER, ACE)), 21, true),
                Arguments.arguments(Arrays.asList(new Card(HEART, ACE), new Card(CLOVER, KING), new Card(DIAMOND, QUEEN)), 21, false)
        );
    }

    @DisplayName("카드 포인트 합이 21을 초과하면 bust이다")
    @ParameterizedTest
    @MethodSource("bustPlayingCardProvider")
    void bust(List<Card> cardList, int point, boolean expected) {
        // given
        // when
        CardWrapper cards = CardWrapper.of(cardList);

        // then
        assertThat(cards.sumPoints()).isEqualTo(point);
        assertThat(cards.isBust()).isEqualTo(expected);
    }

    static Stream<Arguments> bustPlayingCardProvider() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(new Card(HEART, TWO), new Card(CLOVER, KING), new Card(DIAMOND, JACK)), 22, true),
                Arguments.arguments(Arrays.asList(new Card(HEART, ACE), new Card(CLOVER, KING), new Card(DIAMOND, THREE), new Card(SPADE, ACE)), 15, false),
                Arguments.arguments(Arrays.asList(new Card(DIAMOND, JACK), new Card(CLOVER, ACE)), 21, false)
        );
    }
}