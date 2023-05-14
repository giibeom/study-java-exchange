package woowaapplication.pair.exchange.currency.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import woowaapplication.pair.exchange.currency.domain.ClientCurrencyApi;
import woowaapplication.pair.exchange.currency.domain.Currency;
import woowaapplication.pair.exchange.currency.domain.Nations;
import woowaapplication.pair.exchange.currency.infra.clients.dto.CurrencyInfoResponseDto;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyRequestDto;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyResponseDto;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final ClientCurrencyApi clientCurrencyApi;

    @Value("${currency.default.src}")
    private Nations defaultSrc;

    public CurrencyResponseDto calculateCurrency(CurrencyRequestDto currencyRequestDto) {
        CurrencyInfoResponseDto currencyInfoResponseDto =
                clientCurrencyApi.getAll(currencyRequestDto.getSendCountry());

        Currency currency = Currency.of(
                currencyRequestDto.getSendCountry(),
                currencyRequestDto.getReceiveCountry(),
                currencyRequestDto.getAmountToSend(),
                currencyInfoResponseDto.getQuotes()
        );

        currency.calculateDestinationCurrency();

        return CurrencyResponseDto.builder()
                .destination(currency.getDestination())
                .source(currency.getSource())
                .allExchangeRate(currency.getAllExchangeRate())
                .destinationAmount(currency.getDestinationAmount())
                .sourceAmount(currency.getSourceAmount())
                .build();
    }

    public CurrencyResponseDto getAllCurrency() {
        CurrencyInfoResponseDto currencyInfoResponseDto =
                clientCurrencyApi.getAll(defaultSrc);

        Currency currency = Currency.of(defaultSrc, currencyInfoResponseDto.getQuotes());

        return CurrencyResponseDto.builder()
                .destination(currency.getDestination())
                .source(currency.getSource())
                .allExchangeRate(currency.getAllExchangeRate())
                .destinationAmount(currency.getDestinationAmount())
                .sourceAmount(currency.getSourceAmount())
                .build();
    }
}
