package nextstep.blackjack;

import nextstep.blackjack.controller.GameController;

public class BlackjackApplication {
    public static void main(String[] args) {
        new GameController().deal();
    }
}
