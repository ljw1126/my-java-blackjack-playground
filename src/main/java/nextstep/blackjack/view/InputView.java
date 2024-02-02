package nextstep.blackjack.view;

import nextstep.blackjack.model.participant.Participant;
import nextstep.blackjack.model.participant.Player;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String ASK_PLAYER_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String ASK_BET_AMOUNT_FORMAT = "%s의 배팅 금액은?";
    private static final String ASK_DRAW = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
    private static final String NAME_DELIMITER = ",";
    public InputView() {
    }

    public List<Participant> askPlayerName() {
        System.out.println(ASK_PLAYER_NAME);
        Scanner sc = new Scanner(System.in);
        return parse(sc);
    }

    private List<Participant> parse(Scanner sc) {
        try {
            String input = sc.next();
            String[] tokens = input.split(NAME_DELIMITER);
            return create(tokens);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return askPlayerName();
        }
    }

    private List<Participant> create(String[] names) {
        List<Participant> result = new ArrayList<>();
        for(String name : names) {
            result.add(new Player(name.trim()));
        }

        return result;
    }

    public int askBetAmount(String name) {
        System.out.println(String.format(ASK_BET_AMOUNT_FORMAT, name));
        Scanner sc = new Scanner(System.in);

        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return askBetAmount(name);
        }
    }

    public String askDraw(String name) {
        System.out.println(String.format(ASK_DRAW, name));
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
}
