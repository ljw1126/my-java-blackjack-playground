package nextstep.blackjack.model.participant;

public enum GameResult {
    WIN(1.0),
    LOSE(-1.0),
    DRAW(0.0),
    BLACKJACK(0.5);

    private double earningRate;

    GameResult(double earningRate) {
        this.earningRate = earningRate;
    }

    public double getEarningRate() {
        return earningRate;
    }

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }
}
