package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Card;
import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.state.Hit;
import nextstep.blackjack.model.state.State;

public abstract class AbstractParticipant implements Participant {
    protected String name;
    protected State state;
    private int betAmount;
    public AbstractParticipant(String name) {
        this.name = name;
        this.state = new Hit(new Cards());
        this.betAmount = 0;
    }

    @Override
    public void initBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public void draw(Card card) {
        State next = this.state.draw(card);
        setState(next);
    }

    @Override
    public void setState(State next) {
        this.state = next;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Cards cards() {
        return state.cards();
    }

    @Override
    public int getBetAmount() {
        return this.betAmount;
    }

    @Override
    public boolean isBlackjack() {
        return cards().isBlackjack();
    }

    @Override
    public boolean isBust() {
        return cards().isBust();
    }

    @Override
    public Score score() {
        return cards().score();
    }

    @Override
    public String showCards() {
        return cards().joinCardList();
    }
}
