package nextstep.blackjack.controller;

import nextstep.blackjack.model.CardDeck;

public class GameController {

    public void deal() {
        // 플레이어 입장
        System.out.println("게임에 참여할 사람의 이름을 입력하세요");

        // 배팅 금액 입력 -- **의 배팅 금액은?

        // 카드 덱에서 2장씩 뽑아 할당하기
        CardDeck cardDeck = new CardDeck();
        System.out.println(cardDeck.toString());
        // 보유 카드 출력 (이때 딜러는 한장만 공개)

        // GamerPhase
        // DealerPhase

        // 결과 출력
        // 최종 수익 출력
    }
}
