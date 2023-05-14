package woowaapplication.pair.exchange.currency.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import woowaapplication.pair.exchange.currency.application.CurrencyService;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyRequestDto;
import woowaapplication.pair.exchange.currency.application.dto.CurrencyResponseDto;
import woowaapplication.pair.exchange.currency.domain.Nations;

@Controller
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    // 수취국가에 따른 환율 정보 반환 API
    @GetMapping
    public String index(Model model) {
        CurrencyResponseDto currencyResponseDto = currencyService.getAllCurrency();
        model.addAttribute("src", currencyResponseDto.getSource());
        model.addAttribute("dst", Nations.KOREA);
        model.addAttribute("countries", Nations.values());
        model.addAttribute("amountToSend", 0);
        model.addAttribute("rates", currencyResponseDto.getAllExchangeRate());

        return "currencyForm";
    }

    // 수취금액 계산 API
    @PostMapping
    public String getCurrency(@ModelAttribute CurrencyRequestDto currencyRequestDto, Model model) {
        CurrencyResponseDto currencyResponseDto = currencyService.calculateCurrency(currencyRequestDto);

        model.addAttribute("src", currencyResponseDto.getSource());
        model.addAttribute("dst", currencyResponseDto.getDestination());
        model.addAttribute("countries", Nations.values());
        model.addAttribute("amountToSend", currencyResponseDto.getSourceAmount());
        model.addAttribute("rates", currencyResponseDto.getAllExchangeRate());
        model.addAttribute("result", currencyResponseDto.getDestinationAmount());

        return "currencyForm";
    }
}
