package woowaapplication.pair.exchange.currency.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyRequestDto;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyResponseDto;
import woowaapplication.pair.exchange.currency.domain.Nations;
import woowaapplication.pair.exchange.currency.fixture.CurrencyFixture;


@SpringBootTest
class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    @DisplayName("하나의 수취국가에 대한 환율 및 환율 변환 결과 가져오기")
    public void currencyGetOneCurrencyTest (){
        //given
        CurrencyRequestDto 환전_요청_정보 = new CurrencyRequestDto(Nations.USA, Nations.JAPAN,
            1000.0);

        //when
        CurrencyResponseDto 환전_결과 = currencyService.calculateCurrency(
            환전_요청_정보);

        //then
        assertAll(
            () -> assertThat(환전_결과.getDestinationAmount()).isEqualTo(
                환전_요청_정보.getAmountToSend() * CurrencyFixture.일본.환율_정보()),
            () -> assertThat(환전_결과.getDestination()).isEqualTo(
                환전_요청_정보.getReceiveCountry()),
            () -> assertThat(환전_결과.getSource()).isEqualTo(
                환전_요청_정보.getSendCountry())
        );
    }

    @Test
    @DisplayName("모든 국가에 대한 환율 정보 가져오기")
    public void currencyGetAllCurrencyTest (){
        //when
        CurrencyResponseDto 모든_환율정보_조회_결과 = currencyService.getAllCurrency();

        //then
        assertAll(
            () -> assertThat(모든_환율정보_조회_결과.getDestination()).isNull(),
            () -> assertThat(모든_환율정보_조회_결과.getDestinationAmount()).isNull(),
            () -> assertThat(모든_환율정보_조회_결과.getAllExchangeRate())
                    .containsEntry(CurrencyFixture.일본.나라_정보(), CurrencyFixture.일본.환율_정보())
                    .containsEntry(CurrencyFixture.한국.나라_정보(), CurrencyFixture.한국.환율_정보())
                    .containsEntry(CurrencyFixture.필리핀.나라_정보(), CurrencyFixture.필리핀.환율_정보())
        );
    }
}