package nextstep.blackjack.controller;

import nextstep.blackjack.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class GameController {
    private static final String NAME_DELIMITER = ",";
    private static final int INIT_CARD = 2;
    public void play() {
        Scanner sc = new Scanner(System.in);

        // 1. 플레이어 입력
        String[] names = getNames(sc);

        Dealer dealer = new Dealer();
        List<Player> players = initPlayer(names);

        // 2. 배팅 금액 입력 -- **의 배팅 금액은?

        CardDeck cardDeck = new CardDeck();
        // 3. 카드 덱에서 2장씩 뽑아 할당하기 (deal)
        deal(dealer, players, cardDeck);
        // 4. 보유 카드 출력 (이때 딜러는 카드 1장만 공개)
        endDeal(dealer, players);
        printNewLine();

        // 5. 유저 차례

        // 6. 딜러 차례

        // 7. 결과 출력

        // 8. 최종 수익 출력
    }

    private static void endDeal(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getName();
        String playerNames = players.stream().map(Player::getName).collect(joining(NAME_DELIMITER));
        System.out.println(String.format("%s와 %s 에게 패를 2장씩 나누었습니다", dealerName, playerNames));

        dealer.firstCalling();
        for(Player player : players) {
            player.firstCalling();
        }
    }

    private static String[] getNames(Scanner sc) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.next();
        return input.split(NAME_DELIMITER);
    }

    private List<Player> initPlayer(String[] names) {
        return Arrays.stream(names).map(Gamer::new).collect(toList());
    }

    private void deal(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        deal(dealer, cardDeck);
        for(Player player : players) {
            deal(player, cardDeck);
        }
    }

    // 딜러와 플레이어에게 카드 2장씩 나눠준다
    private void deal(Player player, CardDeck cardDeck) {
        for(int i = 1; i <= INIT_CARD; i++) {
            Card card = cardDeck.draw();
            player.receiveCard(card);
        }
    }

    private void printNewLine() {
        System.out.println();
    }
}
