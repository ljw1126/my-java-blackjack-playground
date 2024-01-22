package nextstep.blackjack.model.participant;

import nextstep.blackjack.model.card.Cards;

public class Player extends AbstractParticipant {
    public Player(String name) {
        super(name);
    }

    @Override
    public void firstCalling() {
        calling();
    }

    public BetAmount revenue(Dealer dealer) {
        GameResult gameResult = judgement(dealer);
        if(gameResult.isBlackjack()) {
            return betAmount.applyBlackjackRate();
        }

        if(gameResult.isWin()) {
            return betAmount.applyWinningRate();
        }

        if(gameResult.isLose()) {
            return betAmount.applyLoseRate();
        }

        return betAmount.applyDrawRate();
    }

    public GameResult judgement(Dealer dealer) {
        Cards dealerCards = dealer.getCards();
        Cards playerCards = this.getCards();
        int dealerPoint = dealerCards.sum();
        int playerPoints = playerCards.sum();

        if(isOnlyPlayerBlackjack(dealerCards)) {
            return GameResult.BLACKJACK; // 1.5
        }

        /**
         * 1. 딜러가 Bust아니고, Player도 Bust 아닐 때 Player 점수가 더 높은 경우
         * 2. 딜러가 Bust이고, Player는 살아남은 경우(=Bust아닌 경우)
         */
        if(isPlayerWin(dealerCards, dealerPoint, playerPoints)) {
            return GameResult.WIN; // 1.0
        }

        /**
         * 1. Player가 Bust인 경우
         * 2. 딜러와 Player가 Bust가 아닐 떄 Player 점수가 더 낮은 경우
         */
        if(isPlayerLose(dealerCards, dealerPoint, playerPoints)) {
            return GameResult.LOSE; // -1.0
        }

        return GameResult.DRAW; // 0, 배팅한 금액만 돌려 받음
    }

    private boolean isPlayerWin(Cards dealerCards, int dealerPoint, int playerPoints) {
        return (!dealerCards.isBust() && !isBust() && (dealerPoint < playerPoints))
                || (dealerCards.isBust() && !isBust());
    }

    private boolean isPlayerLose(Cards dealerCards, int dealerPoint, int playerPoints) {
        return isBust() || (!dealerCards.isBust() && !isBust() && playerPoints < dealerPoint);
    }

    private boolean isOnlyPlayerBlackjack(Cards dealerCards) {
        return isBlackjack() && !dealerCards.isBlackjack();
    }

}
