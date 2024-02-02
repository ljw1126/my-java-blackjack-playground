package nextstep.blackjack.model.card;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    public static final int INIT_SIZE = 52;

    private Stack<Card> cardList;

    public Deck() {
        this.cardList = create();
    }

    public static Stack<Card> create() {
        Stack<Card> result = new Stack<>();

        Pattern[] patterns = Pattern.values();
        Denomination[] denominations = Denomination.values();
        for(Pattern pattern : patterns) {
            for(Denomination denomination : denominations) {
                result.push(new Card(pattern, denomination));
            }
        }

        Collections.shuffle(result);

        return result;
    }

    public Card draw() {
        return this.cardList.pop();
    }

    public int size() {
        return this.cardList.size();
    }
}
