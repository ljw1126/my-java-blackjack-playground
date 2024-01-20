package nextstep.blackjack.model;

public abstract class AbstractParticipant implements Participant {

    protected String name;
    protected CardWrapper cards;

    public AbstractParticipant(String name) {
        this.name = name;
        this.cards = new CardWrapper();
    }

    @Override
    public void receiveCard(Card card) {
        cards.add(card);
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

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }
}
