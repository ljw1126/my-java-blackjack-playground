package nextstep.blackjack.model.card;

import java.util.Objects;

public class Score {
    private static final int MAX_SCORE = 21;
    private static final int TEN = 10;
    private int score;

    public Score(int score) {
        this.score = score;
    }

    public void plus(int point) {
        this.score += point;
    }

    public Score plusTenIfNotBust() {
        final Score score = new Score(this.score + TEN);
        if(score.isBust()) {
           return this;
        }

        return score;
    }

    public int getScore() {
        return score;
    }

    public boolean greaterThan(Score other) {
        return score > other.score;
    }

    public boolean isBust() {
        return MAX_SCORE < score;
    }

    public boolean isBlackjackScore() {
        return score == MAX_SCORE;
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
