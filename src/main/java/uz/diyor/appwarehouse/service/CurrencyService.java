package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Currency;
import uz.diyor.appwarehouse.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    final CurrencyRepository currencyRepository;


    public Result addCurrency(Currency currency) {
        if (currencyRepository.existsByName(currency.getName())) {
            return new Result("Already exist currency",false);
        }
        currencyRepository.save(currency);
        return new Result("Currency added",true);
    }

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    public Result deleteCurrency(Integer id) {
        Optional<Currency> currency = currencyRepository.findById(id);
        if (currency.isEmpty()) {
            return new Result("Currency not found",false);
        }
        currencyRepository.deleteById(id);
        return new Result("Currency deleted",true);
    }

    public Result updateCurrency(Integer id, Currency currency) {
        Optional<Currency> optional = currencyRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Currency not found",false);
        }
        Currency currency1 = optional.get();
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return new Result("Currency updated",true);
    }
}
