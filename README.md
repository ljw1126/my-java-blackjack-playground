## 기능 구현 목록 

### 카드 관련 
Pattern 
- [x] 4가지 모양을 가진다

Denomination 
- [x] 총 12개의 끗수를 가진다 (조커 제외)

Card
- [x] Pattern 과 Denomination 속성값을 가진다
- [x] 카드의 점수(Point)를 반환한다

Deck
- [x] 총 52장의 카드로 구성된다 
- [x] 드로우 할 경우 카드 한장을 뽑아준다
  - 이때 사이즈는 1씩 줄어든다
---

### 참가자 관련
Dealer, Player 있을때
- [x] 이름/카드/배팅금액을 가진다 -- 상태 
- [x] 배팅 금액을 설정한다 -- 행위
- [x] 카드를 드로우 하여 손패에 추가한다 -- 행위
- [x] 총 점수를 구한다 -- 행위
- [x] 블랙잭인가 (카드 2장, 점수 21)
- [x] 버스트인가 (점수 21 초과)
- 카드를 보일 때 -- 행위
  -[x] 첫 드로우시 딜러는 카드 한장만 공개한다
  -[x] 그외에 유저와 딜러는 카드를 모두 공개한다(이때 콤마로 연결한다)

---

### 최종 판정 관련 
Rule 클래스에 Dealer, Player 인스턴스를 넘겨 결과를 구한다
<br/>

Dealer 승리하는 경우
- [x] 딜러만 블랙잭인 경우 
- [x] 플레이어만 버스트인 경우 
- [x] 둘 다 버스트가 아니고 딜러의 Score가 더 높은 경우

Player 승리하는 경우
- [x] 플레이어만 블랙잭인 경우 
- [x] 딜러만 버스트인 경우 
- [x] 둘 다 버스트가 아니고 플레이어의 Score가 더 높은 경우

무승부인 경우 
- [x] 둘 다 블랙잭인 경우
- [x] 둘 다 Score가 같은 경우