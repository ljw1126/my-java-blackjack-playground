package nextstep.blackjack.controller;

import nextstep.blackjack.model.card.CardDeck;
import nextstep.blackjack.model.participant.Dealer;
import nextstep.blackjack.model.participant.Player;
import nextstep.blackjack.model.rule.Rule;
import nextstep.blackjack.model.state.StateFactory;
import nextstep.blackjack.view.InputView;
import nextstep.blackjack.view.OutputView;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GameController {

    private static final String DRAWABLE_DEALER = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public void play() {
        // 1. 플레이어 입력
        CardDeck cardDeck = new CardDeck();

        Dealer dealer = new Dealer();
        List<Player> players = initPlayer(InputView.names());

        // 2. 배팅 금액 입력 -- **의 배팅 금액은?
        players = InputView.betAmount(players);

        // 3. 카드 덱에서 2장씩 뽑아 할당하기 (deal)
        deal(dealer, players, cardDeck);
        OutputView.showFirstCards(dealer, players);

        // 5. 유저 차례
        for(Player player : players) {
            InputView.playerPhase(player, cardDeck);
        }

        // 6. 딜러 차례
        dealerPhase(dealer, cardDeck);

        // 7. 결과 출력
        Rule rule = new Rule(dealer, players);
        OutputView.showPlayingCardAndScore(rule.getParticipant());
        OutputView.showResultProfit(rule.judgement());
    }

    private List<Player> initPlayer(String[] names) {
        return Arrays.stream(names).map(Player::new).collect(toList());
    }

    private void deal(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        dealer.setState(StateFactory.create(cardDeck.draw(), cardDeck.draw()));
        for(Player player : players) {
            player.setState(StateFactory.create(cardDeck.draw(), cardDeck.draw()));
        }
    }

    // 딜러는 16 이하면 무조건 한장 더 받고, 17 이상이면 넘긴다
    private void dealerPhase(Dealer dealer, CardDeck cardDeck) {
        if(dealer.drawable()) {
            System.out.println(DRAWABLE_DEALER);
            dealer.receivePlayingCard(cardDeck.draw());
            System.out.println();
        }

        if(!dealer.isBlackjack() && !dealer.isBust()) {
            dealer.stay();
        }
    }
}
