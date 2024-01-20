package nextstep.blackjack.controller;

import nextstep.blackjack.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.util.stream.Collectors.*;

public class GameController {
    private static final String NAME_DELIMITER = ",";
    private static final int INIT_CARD = 2;
    private static final String ANSWER_N = "n";
    private static final String ANSWER_Y = "y";
    public void play() {
        Scanner sc = new Scanner(System.in);

        // 1. 플레이어 입력
        String[] names = getNames(sc);

        Dealer dealer = new Dealer();
        List<Player> players = initPlayer(names);

        // 2. 배팅 금액 입력 -- **의 배팅 금액은?
        requestBetAmountToPlayer(sc, players);

        CardDeck cardDeck = new CardDeck();
        // 3. 카드 덱에서 2장씩 뽑아 할당하기 (deal)
        deal(dealer, players, cardDeck);
        // 4. 보유 카드 출력 (이때 딜러는 카드 1장만 공개)
        endDeal(dealer, players);
        printNewLine();

        // 5. 유저 차례
        for(Player player : players) {
            playerPhase(sc, player, cardDeck);
        }

        // 6. 딜러 차례
        dealerPhase(dealer, cardDeck);
        printNewLine();

        // 7. 결과 출력
        Rule rule = new Rule(dealer, players);
        rule.showPlayCardAndPoint();

        // 8. 최종 수익 출력
        rule.showRevenue(rule.getNameBetAmountResultMap());
    }

    private void requestBetAmountToPlayer(Scanner sc, List<Player> players) {
        for(Player player : players) {
            System.out.println(String.format("%s의 배팅금액은?", player.getName()));

            int betAmount = sc.nextInt();
            player.initBetAmount(betAmount);
        }
    }

    private void playerPhase(Scanner sc, Player player, CardDeck cardDeck) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(y/n)", player.getName());
        while(true) {
            System.out.println(message);
            String input = sc.next();

            if(ANSWER_N.equals(input.toLowerCase())) {
                break;
            }

            player.receiveCard(cardDeck.draw());
            player.calling();

            if(player.isBust()) {
                break;
            }
        }
    }


    // 딜러는 16 이하면 무조건 한장 더 받고, 17 이상이면 넘긴다
    private void dealerPhase(Dealer dealer, CardDeck cardDeck) {
        if(dealer.drawable()) {
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.receiveCard(cardDeck.draw());
        }
    }


    private void endDeal(Dealer dealer, List<Player> players) {
        String dealerName = dealer.getName();
        String playerNames = players.stream().map(Participant::getName).collect(joining(NAME_DELIMITER));
        System.out.println(String.format("%s와 %s 에게 패를 2장씩 나누었습니다", dealerName, playerNames));

        dealer.firstCalling();
        for(Player player : players) {
            player.firstCalling();
        }
    }

    private String[] getNames(Scanner sc) {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        input = input.replaceAll(" ", "");
        return input.split(NAME_DELIMITER);
    }

    private List<Player> initPlayer(String[] names) {
        return Arrays.stream(names).map(Player::new).collect(toList());
    }

    private void deal(Dealer dealer, List<Player> players, CardDeck cardDeck) {
        deal(dealer, cardDeck);
        for(Player player : players) {
            deal(player, cardDeck);
        }
    }

    // 딜러와 플레이어에게 카드 2장씩 나눠준다
    private void deal(Participant player, CardDeck cardDeck) {
        for(int i = 1; i <= INIT_CARD; i++) {
            Card card = cardDeck.draw();
            player.receiveCard(card);
        }
    }

    private void printNewLine() {
        System.out.println();
    }
}
