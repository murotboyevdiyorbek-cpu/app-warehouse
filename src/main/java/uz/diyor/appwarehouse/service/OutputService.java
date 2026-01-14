package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.OutputDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Currency;
import uz.diyor.appwarehouse.entity.Output;
import uz.diyor.appwarehouse.entity.Warehouse;
import uz.diyor.appwarehouse.repository.CurrencyRepository;
import uz.diyor.appwarehouse.repository.OutputRepository;
import uz.diyor.appwarehouse.repository.WarehouseRepository;




import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class OutputService {

    private final OutputRepository outputRepository;
    private final WarehouseRepository warehouseRepository;
    private final CurrencyRepository currencyRepository;



    public  Result addOutput(OutputDto dto) {

        Optional<Warehouse> warehouseOpt =
                warehouseRepository.findById(dto.warehouse().getId());
        if (warehouseOpt.isEmpty()) {
            return new Result("Warehouse not found", false);
        }

        Optional<Currency> currencyOpt =
                currencyRepository.findById(dto.currency().getId());
        if (currencyOpt.isEmpty()) {
            return new Result("Currency not found", false);
        }
        Output output = new Output();
        output.setDate(LocalDateTime.now());
        output.setWarehouse(warehouseOpt.get());
        output.setCurrency(currencyOpt.get());
        output.setFactureNumber(dto.factureNumber());
        output.setCode(generateCode());
        outputRepository.save(output);

        return new Result("Output added", true);
    }

    public String generateCode() {
        Integer maxCode = outputRepository.getMaxCode();
        if (maxCode == null) return "1";
        return String.valueOf(maxCode + 1);
    }


    public List<Output> getAllOutput() {
        return   outputRepository.findAll();

    }

    public Result deleteByIdOutput(Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) {
            return new Result("Output not found", false);
        }
        outputRepository.deleteById(id);
        return new Result("Output deleted", true);
    }


    public Result updateOutput(OutputDto outputDto, Integer id) {
        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) {
            return new Result("Output not found", false);
        }
        Output output = optionalOutput.get();
        output.setDate(LocalDateTime.now());
        output.setCode(generateCode());
        output.setFactureNumber(outputDto.factureNumber());
        output.setCurrency(outputDto.currency());
        output.setWarehouse(outputDto.warehouse());
        outputRepository.save(output);
        return new Result("Output updated", true);
    }
}
