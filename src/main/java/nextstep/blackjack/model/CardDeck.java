package nextstep.blackjack.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class CardDeck {
    private List<Card> deck;
    private static final String[] CARD_PATTERN = {"♣", "♥", "♠", "♦"};
    private static final String[] CARD_VALUE = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "K", "Q", "J"};

    public CardDeck() {
        this.deck = this.generateDeck();
    }

    private List<Card> generateDeck() {
        return Arrays.stream(CARD_PATTERN)
                .flatMap(pattern -> Arrays.stream(CARD_VALUE).map(value -> new Card(pattern, value)))
                .collect(toCollection(LinkedList::new));
    }

    public Card draw() {
        int randomNumber = getRandomNumber();
        Card card = this.deck.get(randomNumber);
        this.deck.remove(randomNumber);
        return card;
    }

    private int size() {
        return this.deck.size();
    }

    private int getRandomNumber() {
        return new Random().nextInt(size());
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
