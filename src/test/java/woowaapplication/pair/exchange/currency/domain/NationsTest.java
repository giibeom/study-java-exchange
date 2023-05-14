package woowaapplication.pair.exchange.currency.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NationsTest {

    @ParameterizedTest
    @CsvSource(delimiter = ',', value = {
        "JAPAN,PHILIPPINES,JPYPHP",
        "KOREA,USA,KRWUSD",
        "USA,PHILIPPINES,USDPHP",
        "USA,KOREA,USDKRW",
    })
    public void getResponseKeyTest(String src, String dst, String expected) {
        //given
        Nations srcNations = Nations.valueOf(src);
        Nations dstNations = Nations.valueOf(dst);

        //when
        String result = Nations.getApiResponseKey(srcNations, dstNations);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(delimiter = ',', value = {
        "KOREA,한국(KRW)",
        "JAPAN,일본(JPY)",
        "USA,미국(USD)",
        "PHILIPPINES,필리핀(PHP)",
    })
    public void getFullNameTest(String nationsString, String expected) {
        //given
        Nations nations = Nations.valueOf(nationsString);

        //when
        String result = nations.getFullName();

        //then
        assertThat(result).isEqualTo(expected);
    }
}
