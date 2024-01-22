package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;
import nextstep.blackjack.model.card.PlayingCard;
import nextstep.blackjack.model.card.Score;

import java.util.List;

public class Dealer extends AbstractParticipant {
    private static final int POINT_BOUNDARY = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void firstCalling() {
        List<PlayingCard> playingCards = cards.list();

        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append("카드 : ").append(playingCards.get(0));

        System.out.println(sb);
    }

    public boolean drawable() {
        Score score = cards.score();
        return score.getScore() <= POINT_BOUNDARY;
    }

    public BetAmount calculateRevenue(List<Player> playerList) {
        BetAmount result = new BetAmount(0);

        for(Player player : playerList) {
            result = result.add(player.revenue(this).toNegative());
        }

        return result;
    }
}
