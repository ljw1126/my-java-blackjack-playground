## 리팩토링
- [X] model 패키지를 분리하고, 클래스 이동
- [X] CardWrapper 를 Cards, Card 를 PlayingCard로 클래스명 수정
- Cards 클래스에서
  - sumPoint()를 구할 때 Denomination enum 클래스까지 책임이 넘어가고 있다
  - [X] 점수에 대한 Wrapper 클래스(Score)를 생성하고 책임을 위임할 수 있도록 한다
  - [X] addAll() 제거 -> 생성자 통해 초기 값을 가진 인스턴스 생성하도록 변경한다
  - [X] Denomination 클래스에서 포인트 합계 관련 메소드 제거

> Note. Score 구할 때, .plusTenIfNotBust()에서 신규 Score 생성하고 score.isBust() 물어보는 부분이 인상 깊다.