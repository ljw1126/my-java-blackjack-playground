## 리팩토링
- [X] model 패키지를 분리하고, 클래스 이동
- [X] CardWrapper 를 Cards, Card 를 PlayingCard로 클래스명 수정
- Cards 클래스에서
  - sumPoint()를 구할 때 Denomination enum 클래스까지 책임이 넘어가고 있다
  - [X] 점수에 대한 Wrapper 클래스(Score)를 생성하고 책임을 위임할 수 있도록 한다
  - [X] addAll() 제거 -> 생성자 통해 초기 값을 가진 인스턴스 생성하도록 변경한다
  - [X] Denomination 클래스에서 포인트 합계 관련 메소드 제거

> Note. Score 구할 때, .plusTenIfNotBust()에서 신규 Score 생성하고 score.isBust() 물어보는 부분이 인상 깊다.

<br/>

### State (상태 패턴 관련)
State 인터페이스 </br>
ㄴ Started 추상 클래스 </br>
&nbsp;&nbsp;&nbsp;ㄴ Running 추상 클래스
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ Hit 클래스
<br/>
&nbsp;&nbsp;&nbsp;ㄴ Finished 추상 클래스 
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ Stay 클래스
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ Bust 클래스
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ㄴ Blackjact 클래스


- [X] state 패키지 생성 후 Hit, Blackjack, Stay, Bust 클래스 추가한다
- [X] 생성자 통해 Cards 인자를 받는다 (공통)
- [X] Stay : 1.0배, Bust : -1.0배, Blackjack : 1.5배로 배율을 가질 수 있도록 한다(Hit제외)
- Hit 상태에서 
  - draw(..) 호출 할 경우
    - [X] cards.isBust() 인 경우 Bust 상태를 반환한다
    - [X] 아닌 경우 Hit 상태를 반환한다
  - stay() 호출할 경우 
    - [X] Stay 상태를 반환한다
- Stay, Bust, Blackjack 상태에서 
  - [X] draw(..) 호출할 경우 UnsupportedOperationException 예외를 반환한다
  - [X] stay() 호출할 경우 UnsupportedOperationException 예외를 반환한다  
  - [X] profit() 호출할 경우 인자로 받은 money와 배율을 곱한 결과를 반환한다
-[X] Finished, Running 추상 클래스 만들어 추상화한다
-[X] Cards 공통관리할 Started 추상 클래스 만들어 추상화한다

### InputView 
- 참가자 이름을 받는다 
- 참가자 배팅 금액을 받는다

### OutputView
- 딜러와 참가자의 이름과 카드를 출력한다
  - [X] 첫 번째 공개일 경우 딜러는 카드 한장만 공개한다
- 게임이 끝나면 딜어와 플레이어의 점수를 출력한다