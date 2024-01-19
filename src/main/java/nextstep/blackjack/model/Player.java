package nextstep.blackjack.model;

public interface Player {
    String getName();
    void firstCalling();

    void calling();

    void receiveCard(Card card);

    int sumPoint();
}
