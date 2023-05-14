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

    public Nations 나라_정보() {
        return 나라;
    }

    public Double 환율_정보() {
        return 환율;
    }

    public static Map<String, Double> 송금국가_기준_모든_환율정보(Nations 송금국가) {
        return Arrays.stream(CurrencyFixture.values())
            .collect(Collectors.toMap(
                (nations) -> Nations.getApiResponseKey(송금국가, nations.나라_정보()),
                (nations) -> nations.환율_정보()
            ));
    }

    public static Double 환율_정보(Nations 송금국가) {
        return Arrays.stream(CurrencyFixture.values())
            .filter(currencyFixture -> currencyFixture.나라_정보() == 송금국가)
            .map(currencyFixture -> currencyFixture.환율_정보())
            .findFirst()
            .orElseThrow(
                () -> new RuntimeException("can not extract currency by " + 송금국가)
            );
    }
}
