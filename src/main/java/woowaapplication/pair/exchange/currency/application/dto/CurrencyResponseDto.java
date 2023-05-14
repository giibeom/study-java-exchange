package woowaapplication.pair.exchange.currency.application.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import woowaapplication.pair.exchange.currency.domain.Nations;

@Getter
public class CurrencyResponseDto {

    // 송금 국가
    private final Nations source;

    // 수취국가
    private final Nations destination;

    // 송금액
    private final Double sourceAmount;

    // 수취금액
    private final Double destinationAmount;

    private final Map<Nations, Double> allExchangeRate;

    @Builder
    public CurrencyResponseDto(Nations source, Nations destination, Double sourceAmount, Double destinationAmount,
                               Map<Nations, Double> allExchangeRate) {
        this.source = source;
        this.destination = destination;
        this.sourceAmount = sourceAmount;
        this.destinationAmount = destinationAmount;
        this.allExchangeRate = allExchangeRate;
    }
}
