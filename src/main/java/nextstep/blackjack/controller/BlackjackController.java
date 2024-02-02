package nextstep.blackjack.controller;

import nextstep.blackjack.model.card.Deck;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.rule.Rule;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.OutputView;

import java.util.List;

public class BlackjackController {
    public void run() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        List<Participant> players = inputView.askPlayerName();

        // 배팅 금액 설정
        players = initBetAmount(inputView, players);

        deal(deck, dealer, players);
        outputView.showFirstTurnCards(dealer, players);

        playerPhase(deck, players, inputView);
        dealerPhase(deck, dealer);

        Rule rule = new Rule(dealer, players);
        outputView.showCardsAndScore(dealer, players);
        outputView.showResultProfit(rule.getResultMap());
    }

    private void dealerPhase(Deck deck, Dealer dealer) {
        if(dealer.drawable()) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.draw(deck.draw());
            System.out.println();
        }
    }

    private void playerPhase(Deck deck, List<Participant> players, InputView inputView) {
        for(Participant player : players) {
            askDraw(deck, player, inputView);
        }
    }

    private void askDraw(Deck deck, Participant player, InputView inputView) {
        final String ANSWER_N = "n";
        while(true) {
            String answer = inputView.askDraw(player.getName());

            if (ANSWER_N.equals(answer)) {
                break;
            }

            player.draw(deck.draw());
            System.out.println(player.showCards());

            if (player.isBust()) {
                break;
            }
        }
    }

    private void deal(Deck deck, Dealer dealer, List<Participant> players) {
        dealer.draw(deck.draw());
        dealer.draw(deck.draw());

        for(Participant player : players) {
            player.draw(deck.draw());
            player.draw(deck.draw());
        }
    }

    private List<Participant> initBetAmount(InputView inputView, List<Participant> players) {
        for(Participant player : players) {
            int betAmount = inputView.askBetAmount(player.getName());
            player.initBetAmount(betAmount);
        }

        return players;
    }
}
