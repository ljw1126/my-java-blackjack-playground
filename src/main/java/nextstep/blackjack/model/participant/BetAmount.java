package nextstep.blackjack.model.participant;

import java.util.Objects;

public class BetAmount {

    public static final BetAmount ZERO_BETAMOUNT = new BetAmount(0);

    private double amount;

    public BetAmount(double amount) {
        this.amount = amount;
    }

    public BetAmount initBetAmount(double initBetAmount) {
        return new BetAmount(initBetAmount);
    }

    public double amount() {
        return amount;
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

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
