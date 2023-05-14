package woowaapplication.pair.exchange.currency.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
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
        CurrencyRequestDto currencyRequestDto = new CurrencyRequestDto(Nations.USA, Nations.JAPAN,
            1000.0);

        //when
        CurrencyResponseDto currencyResponseDto = currencyService.calculateCurrency(
            currencyRequestDto);

        //then
        assertAll(
            () -> assertThat(currencyResponseDto.getDestinationAmount()).isEqualTo(
                currencyRequestDto.getAmountToSend() * CurrencyFixture.일본.get환율()),
            () -> assertThat(currencyResponseDto.getDestination()).isEqualTo(
                currencyRequestDto.getReceiveCountry()),
            () -> assertThat(currencyResponseDto.getSource()).isEqualTo(
                currencyRequestDto.getSendCountry())
        );
    }

    @Test
    @DisplayName("모든 국가에 대한 환율 정보 가져오기")
    public void currencyGetAllCurrencyTest (){
        //when
        CurrencyResponseDto allCurrencyResponseDto = currencyService.getAllCurrency();

        //then
        assertAll(
            () -> assertThat(allCurrencyResponseDto.getDestination()).isNull(),
            () -> assertThat(allCurrencyResponseDto.getDestinationAmount()).isNull(),
            () -> assertThat(allCurrencyResponseDto.getAllExchangeRate())
                    .containsEntry(CurrencyFixture.일본.get나라(), CurrencyFixture.일본.get환율())
                    .containsEntry(CurrencyFixture.한국.get나라(), CurrencyFixture.한국.get환율())
                    .containsEntry(CurrencyFixture.필리핀.get나라(), CurrencyFixture.필리핀.get환율())
        );
    }
}