package nextstep.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Gamer implements Player {
    private String name;
    private List<Card> cardList = new ArrayList<>();

    public Gamer(String name) {
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        cardList.add(card);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void firstCalling() {
        calling();
    }

    // 카드패를 보이다
    @Override
    public void calling() {
        StringBuilder sb = new StringBuilder();

        sb.append(getName()).append("카드 : ");
        for(int i = 0; i < cardList.size(); i++) {
            appendComma(sb, i);
            sb.append(cardList.get(i));
        }

        System.out.println(sb);
    }

    @Override
    public int sumPoint() {
        return cardList.stream()
                .map(Card::getDenomination)
                .map(Denomination::getPoint)
                .reduce(0, Integer::sum);
    }

    private static void appendComma(StringBuilder sb, int i) {
        if(i != 0) sb.append(", ");
    }
}
