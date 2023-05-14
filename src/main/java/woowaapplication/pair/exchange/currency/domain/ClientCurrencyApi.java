package woowaapplication.pair.exchange.currency.domain;

import woowaapplication.pair.exchange.currency.infra.clients.dto.CurrencyInfoResponseDto;

public interface ClientCurrencyApi {

    CurrencyInfoResponseDto getAll(Nations src);
}
