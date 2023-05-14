package woowaapplication.pair.exchange.currency.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import org.assertj.core.api.Assertions;
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
    public void calcCurrencyResultTest (String src, String dst){

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

    @Test
    public void calcCurrencyBeforeSettingThrows() {
        //given
        Nations srcNations = Nations.USA;
        Map<String, Double> quotes = CurrencyFixture.getQuotes(srcNations);

        //when
        currency = Currency.of(srcNations, quotes);

        // TODO: 2023/05/14 Currency 획득 후 dst정보가 없을때 저희가 예상한 @IllegalStateException이 아닌, NPE가 발생합니다. 로직을 수정해야 하지 않을까요?

        //then
        assertThatThrownBy(
            () -> currency.calculateDestinationCurrency()
        ).isInstanceOf(NullPointerException.class);
    }
}