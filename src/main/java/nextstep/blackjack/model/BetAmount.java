package nextstep.blackjack.model;

import java.util.Objects;

public class BetAmount {

    public static final BetAmount ZERO_BETAMOUNT = new BetAmount(0);

    private double amount;

    public BetAmount(double amount) {
        this.amount = amount;
    }

    public static BetAmount of(double amount) {
        return new BetAmount(amount);
    }

    public BetAmount initBetAmount(double initBetAmount) {
        return new BetAmount(initBetAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetAmount betAmount = (BetAmount) o;
        return Double.compare(betAmount.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public BetAmount applyBlackjackRate() {
        return new BetAmount(this.amount * GameResult.BLACKJACK.getEarningRate());
    }

    public BetAmount applyWinningRate() {
        return new BetAmount(this.amount * GameResult.WIN.getEarningRate());
    }

    public BetAmount applyLoseRate() {
        return new BetAmount(this.amount * GameResult.LOSE.getEarningRate());
    }

    public BetAmount applyDrawRate() {
        return new BetAmount(this.amount * GameResult.DRAW.getEarningRate());
    }

    public BetAmount add(BetAmount other) {
        return new BetAmount(this.amount + other.amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }

    public BetAmount toNegative() {
        return new BetAmount(this.amount * -1);
    }
}
