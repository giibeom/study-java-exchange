package woowaapplication.pair.exchange.currency.infra.clients;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import woowaapplication.pair.exchange.currency.domain.ClientCurrencyApi;
import woowaapplication.pair.exchange.currency.domain.Nations;
import woowaapplication.pair.exchange.currency.infra.clients.dto.CurrencyInfoResponseDto;

@Component
@RequiredArgsConstructor
public class ClientCurrencyApiImpl implements ClientCurrencyApi {

    private final RestTemplate restTemplate;

    @Value("${currency.api.endpoint}")
    private String endPoint;
    @Value("${currency.api.key}")
    private String key;


    public CurrencyInfoResponseDto get(Nations src, Nations dst) {
        String uri = getURI(src, dst);
        HttpEntity<Object> httpEntity = getHttpEntityWithAuth();

        ResponseEntity<CurrencyInfoResponseDto> response = restTemplate.exchange(
                uri, HttpMethod.GET, httpEntity, CurrencyInfoResponseDto.class
        );

        if (response.getBody().getQuotes().isEmpty()) {
            throw new RuntimeException("Currency Api Execute Error");
        }

        return response.getBody();
    }


    public CurrencyInfoResponseDto getAll(Nations src) {
        String uri = getURI(src, Nations.values());
        HttpEntity<Object> httpEntity = getHttpEntityWithAuth();

        ResponseEntity<CurrencyInfoResponseDto> response = restTemplate.exchange(
                uri, HttpMethod.GET, httpEntity, CurrencyInfoResponseDto.class
        );

        if (response.getBody().getQuotes().isEmpty()) {
            throw new RuntimeException("Currency Api Execute Error");
        }

        return response.getBody();
    }

    private HttpEntity<Object> getHttpEntityWithAuth() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.add("apikey", key);

        return new HttpEntity<>(httpHeader);
    }

    private String getURI(Nations src, Nations... dst) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(endPoint);
        uriComponentsBuilder.queryParam("source", src.getCurrency());

        String joinValues = Arrays.stream(dst)
                .map(Nations::getCurrency)
                .collect(Collectors.joining(","));

        uriComponentsBuilder.queryParam("currencies", joinValues);

        return uriComponentsBuilder.toUriString();
    }
}
