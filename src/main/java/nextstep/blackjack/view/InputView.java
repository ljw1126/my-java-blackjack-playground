package nextstep.blackjack.view;

import nextstep.blackjack.model.card.CardDeck;
import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_DELIMITER = ",";
    private static final String ANSWER_N = "n";
    private static final Scanner sc = new Scanner(System.in);
    private static final String INPUT_PARTICIPANT_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String INPUT_BET_AMOUNT_FORMAT = "%s의 배팅금액은?";
    private static final String INPUT_DRAW_QUESTION = "%s는 한장의 카드를 더 받겠습니까?(y/n)";

    public static String[] names() {
        System.out.println(INPUT_PARTICIPANT_NAME);
        String input = sc.nextLine();
        input = input.replaceAll(" ", "");
        return input.split(NAME_DELIMITER);
    }

    public static List<Player> betAmount(List<Player> players) {
        for(Player player : players) {
            int bet = askBetAmount(sc, player);
            player.initBetAmount(bet);
        }

        return players;
    }

    public static int askBetAmount(Scanner sc, Player player) {
        try {
            System.out.println(String.format(INPUT_BET_AMOUNT_FORMAT, player.getName()));
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return askBetAmount(sc, player);
        }
    }

    public static void playerPhase(Participant participant, CardDeck cardDeck) {
        String message = String.format(INPUT_DRAW_QUESTION, participant.getName());
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
