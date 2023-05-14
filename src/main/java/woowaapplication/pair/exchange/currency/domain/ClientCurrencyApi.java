package woowaapplication.pair.exchange.currency.domain;

import woowaapplication.pair.exchange.currency.infra.clients.dto.CurrencyInfoResponseDto;

public interface ClientCurrencyApi {

    CurrencyInfoResponseDto get(Nations src, Nations dst);

    CurrencyInfoResponseDto getAll(Nations src);
}
