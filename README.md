# woowayouth-exchange

# 미션 - 환율 계산

## 👥 페어 멤버
| ALEX | John                                                                                                        |
| --- |-------------------------------------------------------------------------------------------------------------|
[<img src="https://avatars.githubusercontent.com/u/59248326?v=4" width="100">](https://github.com/giibeom)| [<img src="https://avatars.githubusercontent.com/u/19621155?v=4" width="100">](https://github.com/jhsong2580) |

---

## 💻 Github 컨벤션

|    타입    |                 설명                 |                  예시                   |
|:--------:|:----------------------------------:|:-------------------------------------:|
|  merge   |              PR merge              |         merge: 백엔드 프로젝트를 생성한다         |
|   feat   |             새로운 기능 추가              |         feat: 사용자 등록 기능을 추가한다         |
|   fix    |               버그 수정                | fix: 예약 시 매장 회원에게 승인 요청이 안가는 오류를 수정한다 |
| refactor |              코드 리팩토링               |    refactor: 예약 비즈니스 로직 리팩토링을 진행한다    |
|   test   |             테스트 코드 작성              |         test: 예약 실패 케이스를 추가한다         |
|   docs   |               문서 수정                |        docs: README에 ERD를 추가한다        |
|  chore   | 빌드 업무, 패키지 매니저 수정 등 (소스 코드 외적인 작업) |          chore: PR 템플릿을 생성한다          |

---

## 🚀 객체 정의

- RestTemplateSetting, RestApiErrorHandler
  - RestTemplate 설정 및 Bean 등록
- CurrencyController
  - View를 위한 정보를 Model에 담아 View Name을 반환
- CurrencyService
  - 환율 정보 요청에 필요한 유스케이스
- ClientCurrencyApi
  - 필요한 API 공통 기능 정의
- ClientCurrencyApiImpl
  - API 구현체
- Currency
  - 환율 관련 비즈니스 로직을 담당하는 도메인 객체
---

## 🎯 패키지 구조

```markdown
`-- exchange : 베이스 패키지
    |-- ExchangeApplication.java
    |-- common : 공통
    |   `-- config
    |       |-- RestApiErrorHandler.java
    |       `-- RestTemplateSetting.java
    `-- currency : 환율 관련
        |-- application : 애플리케이션 계층
        |   |-- CurrencyService.java
        |   `-- dto
        |       |-- CurrencyRequestDto.java
        |       `-- CurrencyResponseDto.java
        |-- domain : 도메인 계층
        |   |-- ClientCurrencyApi.java
        |   |-- Currency.java
        |   `-- Nations.java
        |-- infra : 인프라 계층
        |   `-- clients : 외부 API 관련
        |       |-- ClientCurrencyApiImpl.java
        |       `-- dto
        |           `-- CurrencyInfoResponseDto.java
        `-- presentation : UI 계층
            `-- CurrencyController.java
```
