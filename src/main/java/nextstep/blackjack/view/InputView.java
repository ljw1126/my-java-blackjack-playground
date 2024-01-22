package nextstep.blackjack.view;

import nextstep.blackjack.model.card.CardDeck;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_DELIMITER = ",";
    private static final String ANSWER_N = "n";
    private static final Scanner sc = new Scanner(System.in);

    public static String[] names() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        input = input.replaceAll(" ", "");
        return input.split(NAME_DELIMITER);
    }

    public static List<Player> betAmount(List<Player> players) {
        for(Player player : players) {
            System.out.println(String.format("%s의 배팅금액은?", player.getName()));

            int betAmount = sc.nextInt();
            player.initBetAmount(betAmount);
        }

        return players;
    }

    public static void playerPhase(Participant participant, CardDeck cardDeck) {
        String message = String.format("%s는 한장의 카드를 더 받겠습니까?(y/n)", participant.getName());
        while(true) {
            System.out.println(message);
            String input = sc.next();

            if(ANSWER_N.equals(input.toLowerCase())) {
                participant.stay(); // 블랙잭 상태에서 n날리면 예외 발생
                break;
            }

            participant.receivePlayingCard(cardDeck.draw());
            OutputView.showCards(participant);

            if(participant.isFinished()) {
                break;
            }
        }
    }
}
