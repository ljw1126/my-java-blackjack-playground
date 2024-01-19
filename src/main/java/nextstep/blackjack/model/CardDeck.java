package nextstep.blackjack.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

public class CardDeck {
    private Stack<Card> deck;
    public CardDeck() {
        this.deck = this.generateDeck();
    }

    private Stack<Card> generateDeck() {
        Stack<Card> playingCard = Arrays.stream(Pattern.values())
                .flatMap(pattern -> Arrays.stream(Denomination.values()).map(denomination -> new Card(pattern, denomination)))
                .collect(Collectors.toCollection(Stack::new));

        Collections.shuffle(playingCard);
        return playingCard;
    }

    public Card draw() {
        return this.deck.pop();
    }

    public int size() {
        return this.deck.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Card card : deck) {
            sb.append(card).append("\n");
        }

        return sb.toString();
    }
}
