package nextstep.blackjack.model;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {
    private Stack<Card> deck;
    public CardDeck() {
        this.deck = this.generateDeck();
    }

    private Stack<Card> generateDeck() {
        Stack<Card> playingCard = new Stack<>();

        for(Pattern pattern : Pattern.values()) {
            for(Denomination denomination : Denomination.values()) {
                playingCard.push(new Card(pattern, denomination));
            }
        }

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
