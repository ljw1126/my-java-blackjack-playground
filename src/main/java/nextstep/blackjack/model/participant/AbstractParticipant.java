package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;
import nextstep.blackjack.model.card.Score;
import nextstep.blackjack.model.state.Hit;
import nextstep.blackjack.model.state.State;

public abstract class AbstractParticipant implements Participant {

    protected String name;
    protected BetAmount betAmount;

    protected State state;

    public AbstractParticipant(String name) {
        this.name = name;
        this.betAmount = BetAmount.ZERO_BETAMOUNT;
        this.state = new Hit(new Cards());
    }

    @Override
    public void receivePlayingCard(PlayingCard card) {
        State next = this.state.draw(card);
        setState(next);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Cards cards() {
        return state.cards();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State state() {
        return state;
    }

    @Override
    public String joiningPlayingCard() {
        return cards().joinPlayingCard();
    }

    @Override
    public boolean isBust() {
        return cards().isBust();
    }

    @Override
    public boolean isBlackjack() {
        return cards().isBlackjack();
    }

    @Override
    public Score score() {
        return cards().score();
    }

    @Override
    public double profit() {
        return state.profit(betAmount.amount());
    }

    @Override
    public void stay() {
        State stay = state.stay();
        setState(stay);
    }

    @Override
    public boolean isFinished() {
        return this.state.isFinished();
    }

    public void initBetAmount(double initBetAmount) {
        this.betAmount = this.betAmount.initBetAmount(initBetAmount);
    }
}
