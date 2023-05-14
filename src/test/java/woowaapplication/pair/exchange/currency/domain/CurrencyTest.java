package woowaapplication.pair.exchange.currency.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import woowaapplication.pair.exchange.currency.fixture.CurrencyFixture;

class CurrencyTest {

    private Currency currency;

    @ParameterizedTest
    @CsvSource(delimiter = ',',
            value = {
                    "USA,KOREA",
                    "USA,JAPAN",
                    "USA,PHILIPPINES"
            }
    )
    public void calcCurrencyResultTest(String src, String dst) {

        //given
        Nations srcNations = Nations.valueOf(src);
        Nations dstNations = Nations.valueOf(dst);
        Map<String, Double> quotes = CurrencyFixture.getQuotes(srcNations);
        double amount = 10_000;

        //when
        currency = Currency.of(srcNations, dstNations, amount, quotes);
        currency.calculateDestinationCurrency();
        Double result = currency.getDestinationAmount();

        //then
        assertThat(result).isEqualTo(amount * CurrencyFixture.get환율By(dstNations));
    }

    @DisplayName("수취 국가와 송금액 정보가 없을 경우 예외를 던진다")
    @Test
    void calcCurrencyBeforeSettingThrows() {
        //given
        Nations srcNations = Nations.USA;
        Map<String, Double> quotes = CurrencyFixture.getQuotes(srcNations);

        //when
        currency = Currency.of(srcNations, quotes);

        //then
        assertThatThrownBy(
                () -> currency.calculateDestinationCurrency()
        ).isInstanceOf(IllegalStateException.class);
    }
}
