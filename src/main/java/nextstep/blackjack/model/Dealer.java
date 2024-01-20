package nextstep.blackjack.model;

public class Dealer extends AbstractParticipant {
    private static final int POINT_BOUNDARY = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void firstCalling() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append("카드 : ").append(cards.get(0));

        System.out.println(sb);
    }

    public boolean drawable() {
        return getCards().sumPoints() <= POINT_BOUNDARY;
    }
}
