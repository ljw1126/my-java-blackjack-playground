package nextstep.blackjack.model.participant;

import java.util.Objects;

public class Score {
    private static final int TEN = 10;
    private static final int MAX_SCORE = 21;
    private int score;

    public Score() {
        this(0);
    }
    
    public Score(int score) {
        this.score = score;
    }

    public boolean isSame(int other) {
        return this.score == other;
    }

    public Score plusTenIfNotBust() {
        int result = this.score + TEN;
        if(isNotBust(result)) {
            this.score = result;
        }

        return this;
    }

    private boolean isNotBust(int sum) {
        return sum <= MAX_SCORE;
    }

    public boolean over(int maxPoint) {
        return this.score > maxPoint;
    }

    public boolean less(int point) {
        return this.score <= point;
    }

    public boolean greaterThan(Score other) {
        return this.score > other.score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
