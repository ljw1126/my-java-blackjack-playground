package nextstep.blackjack.model;

public class Player extends AbstractParticipant {
    public Player(String name) {
        super(name);
    }

    @Override
    public void firstCalling() {
        calling();
    }

}
