# woowayouth-exchange

### 깃 커밋 컨벤션 
- feat, fix, refactor, test
- subject : ~~를 구현한다  

### 기능정의
1. Backend
   1. API를 통해 환율 정보를 가져온다. 
   2. 뷰로부터 수취국가와 USD를 입력받는다.
      - 한국, 일본, 필리핀만 수취국가로 가능하다. 
      - USD는 0 보다 커야한다.
   3. 환율을 통해 금액을 계산한다. 
2. FrontEnd
   1. 계산된 금액을 뷰로 보여준다. 
      - 숫자 3개당 , 찍기 
      - 소숫점은 최대 2자리까지, 만약 "189"여도 "189.00"으로 출력
      - 수취 나라 별 화폐 단위 출력
   2. 잘못된 입력 발생시 에러 메세지를 뷰로 보여준다. 

### Class정의
1. ClientApiUtils
   - 외부 API 요청에 대한 책임을 가짐 
2. CurrencyConvertor
   - 송금액과 수취국가 정보를 기반으로 최종 금액 계산
3. CurrencyController
   - 송금액과 수취국가를 사용자로부터 받는다
   - 요청 데이터에 대한 검증을 한다
   - 계산된 금액과 수취국가 정보를 Model에 담아 View 이름을 반환한다
   - 만약 에러 발생시 에러 메세지를 Model에 담아 반환한다