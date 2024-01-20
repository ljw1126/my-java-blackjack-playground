package nextstep.blackjack.model;

import java.util.List;

public abstract class AbstractParticipant implements Participant {

    protected String name;
    protected CardWrapper cards;
    protected BetAmount betAmount;

    public AbstractParticipant(String name) {
        this.name = name;
        this.cards = new CardWrapper();
        this.betAmount = BetAmount.ZERO_BETAMOUNT;
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
    }

    @Override
    public void receiveCards(List<Card> cardList) {
        cards.addAll(cardList);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public CardWrapper getCards() {
        return cards;
    }

    @Override
    public void calling() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append("카드 : ").append(cards.joinPlayingCard());
        System.out.println(sb);
    }

    @Override
    public boolean isBust() {
        return cards.isBust();
    }

    @Override
    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public void initBetAmount(double initBetAmount) {
        this.betAmount = this.betAmount.initBetAmount(initBetAmount);
    }
}
