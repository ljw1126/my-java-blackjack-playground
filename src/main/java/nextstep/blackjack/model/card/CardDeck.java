package nextstep.blackjack.model.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {
    private Stack<PlayingCard> deck;
    public CardDeck() {
        this.deck = this.generateDeck();
    }

    private Stack<PlayingCard> generateDeck() {
        Stack<PlayingCard> playingCard = Arrays.stream(Pattern.values())
                .flatMap(pattern -> Arrays.stream(Denomination.values()).map(denomination -> new PlayingCard(pattern, denomination)))
                .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(playingCard);
        return playingCard;
    }

    public PlayingCard draw() {
        return this.deck.pop();
    }

    public int size() {
        return this.deck.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(PlayingCard card : deck) {
            sb.append(card).append("\n");
        }

        return sb.toString();
    }
}
