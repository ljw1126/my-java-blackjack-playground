package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;

public abstract class AbstractParticipant implements Participant {
    protected String name;
    protected Cards cards;
    private int betAmount;
    public AbstractParticipant(String name) {
        this(name, new Cards());
    }

    public AbstractParticipant(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
        this.betAmount = 0;
    }

    @Override
    public void initBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public void draw(Card card) {
        this.cards.add(card);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Cards getCards() {
        return this.cards;
    }

    @Override
    public int getBetAmount() {
        return this.betAmount;
    }

    @Override
    public boolean isBlackjack() {
        return this.cards.isBlackjack();
    }

    @Override
    public boolean isBust() {
        return this.cards.isBust();
    }

    @Override
    public Score score() {
        return this.cards.score();
    }

    @Override
    public String showCards() {
        return cards.joinCardList();
    }
}
