package woowaapplication.pair.exchange.currency.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class Currency {

    private final Nations source; // 송금 국가

    private final Nations destination; // 수취국가

    private final Double sourceAmount; // 송금액

    private final Map<Nations, Double> allExchangeRate; // 환율

    private Double destinationAmount; // 수취금액

    public static Currency of(Nations src, Nations dst, double sourceAmount, Map<String, Double> quotes) {
        return new Currency(
                src, dst, sourceAmount, quotes
        );
    }

    public static Currency of(Nations src, Map<String, Double> quotes) {
        return new Currency(
                src,
                null,
                null,
                quotes
        );
    }

    private Currency(Nations source, Nations destination, Double sourceAmount, Map<String, Double> quotes) {
        this.source = source;
        this.destination = destination;
        this.sourceAmount = sourceAmount;
        this.allExchangeRate = Arrays.stream(Nations.values()) // 환율 정보 저장
                .filter(dst -> dst != source)
                .collect(
                        Collectors.toMap(
                                dst -> dst,
                                dst -> quotes.get(Nations.getApiResponseKey(source, dst))
                        )
                );
    }

    public void calculateDestinationCurrency() {
        if (allExchangeRate == null) {
            throw new IllegalStateException("환율 정보가 없습니다");
        }

        this.destinationAmount = allExchangeRate.get(destination) * sourceAmount; // 환율 계산 결과
    }
}
