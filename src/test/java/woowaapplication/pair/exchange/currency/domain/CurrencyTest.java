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
    @DisplayName("Currency 도메인을 통해 환전 결과를 가져온다")
    public void calcCurrencyResultTest(String 송금국가, String 수취국가) {

        //given
        Nations 송금국가정보 = Nations.valueOf(송금국가);
        Nations 수취국가정보 = Nations.valueOf(수취국가);
        Map<String, Double> quotes = CurrencyFixture.송금국가_기준_모든_환율정보(송금국가정보);
        double amount = 10_000;

        //when
        currency = Currency.of(송금국가정보, 수취국가정보, amount, quotes);
        currency.calculateDestinationCurrency();
        Double result = currency.getDestinationAmount();

        //then
        assertThat(result).isEqualTo(amount * CurrencyFixture.환율_정보(수취국가정보));
    }

    @DisplayName("수취 국가와 송금액 정보가 없을 경우 예외를 던진다")
    @Test
    void calcCurrencyBeforeSettingThrows() {
        //given
        Nations srcNations = Nations.USA;
        Map<String, Double> quotes = CurrencyFixture.송금국가_기준_모든_환율정보(srcNations);

        //when
        currency = Currency.of(srcNations, quotes);

        //then
        assertThatThrownBy(
                () -> currency.calculateDestinationCurrency()
        ).isInstanceOf(IllegalStateException.class);
    }
}
