package woowaapplication.pair.exchange.currency.fixture;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import woowaapplication.pair.exchange.currency.domain.Nations;

public enum CurrencyFixture {

    // ----- 정상 값 -----
    한국(Nations.KOREA, 1300.00),
    필리핀(Nations.PHILIPPINES, 1500.00),
    일본(Nations.JAPAN, 1800.00);

    private final Nations 나라;
    private final Double 환율;

    CurrencyFixture(Nations 나라, Double 환율) {
        this.나라 = 나라;
        this.환율 = 환율;
    }

    public Nations get나라() {
        return 나라;
    }

    public Double get환율() {
        return 환율;
    }

    public static Map<String, Double> getQuotes(Nations src) {
        return Arrays.stream(CurrencyFixture.values())
            .collect(Collectors.toMap(
                (nations) -> Nations.getApiResponseKey(src, nations.get나라()),
                (nations) -> nations.get환율()
            ));
    }

    public static Double get환율By(Nations src) {
        return Arrays.stream(CurrencyFixture.values())
            .filter(currencyFixture -> currencyFixture.get나라() == src)
            .map(currencyFixture -> currencyFixture.get환율())
            .findFirst()
            .orElseThrow(
                () -> new RuntimeException("can not extract currency by " + src)
            );
    }
}
