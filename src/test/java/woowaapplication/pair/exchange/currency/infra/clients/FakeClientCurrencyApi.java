package woowaapplication.pair.exchange.currency.infra.clients;

import java.util.Map;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import woowaapplication.pair.exchange.currency.domain.ClientCurrencyApi;
import woowaapplication.pair.exchange.currency.domain.Nations;
import woowaapplication.pair.exchange.currency.fixture.CurrencyFixture;
import woowaapplication.pair.exchange.currency.infra.clients.dto.CurrencyInfoResponseDto;

@Component
@Primary
class FakeClientCurrencyApi implements ClientCurrencyApi {

    @Override
    public CurrencyInfoResponseDto getAll(Nations src) {

        Map<String, Double> quotes = CurrencyFixture.송금국가_기준_모든_환율정보(src);

        return new CurrencyInfoResponseDto(quotes);
    }
}