package woowaapplication.pair.exchange.currency.infra.clients.dto;

import java.util.Map;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CurrencyInfoResponseDto {

    private Map<String, Double> quotes;

    public CurrencyInfoResponseDto(Map<String, Double> quotes) {
        this.quotes = quotes;
    }
}
