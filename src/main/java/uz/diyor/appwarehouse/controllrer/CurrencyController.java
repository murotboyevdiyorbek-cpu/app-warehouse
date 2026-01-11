package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Currency;
import uz.diyor.appwarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CurrencyController {

    final CurrencyService currencyService;

    @PostMapping
    public Result addCurrency(Currency currency) {
        return currencyService.addCurrency(currency);
    }

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrency();
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id) {
        return currencyService.deleteCurrency(id);
    }

    @PutMapping("/{id}")
    public Result updateCurrency(@PathVariable Integer id,
                                 @RequestBody Currency currency) {
        return currencyService.updateCurrency(id,currency);
    }


}
