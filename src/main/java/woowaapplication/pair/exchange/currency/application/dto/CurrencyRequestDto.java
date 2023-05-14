package woowaapplication.pair.exchange.currency.application.dto;

import lombok.Getter;
import woowaapplication.pair.exchange.currency.domain.Nations;

@Getter
public class CurrencyRequestDto {

    private final Nations sendCountry;

    private final Nations receiveCountry;

    private final Double amountToSend;

    public CurrencyRequestDto(Nations sendCountry, Nations receiveCountry, Double amountToSend) {
        this.sendCountry = sendCountry;
        this.receiveCountry = receiveCountry;
        this.amountToSend = amountToSend;
    }
}
