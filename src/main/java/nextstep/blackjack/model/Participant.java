package nextstep.blackjack.model;

public interface Participant {
    String getName();
    void firstCalling();
    void calling();
    void receiveCard(Card card);
    CardWrapper getCards();
}
