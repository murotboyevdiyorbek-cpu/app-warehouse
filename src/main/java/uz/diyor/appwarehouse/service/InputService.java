package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.InputDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Currency;
import uz.diyor.appwarehouse.entity.Input;
import uz.diyor.appwarehouse.entity.Supplier;
import uz.diyor.appwarehouse.entity.Warehouse;
import uz.diyor.appwarehouse.repository.CurrencyRepository;
import uz.diyor.appwarehouse.repository.InputRepository;
import uz.diyor.appwarehouse.repository.SupplierRepository;
import uz.diyor.appwarehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputService {

    final InputRepository inputRepository;
    final WarehouseRepository warehouseRepository;
    final SupplierRepository supplierRepository;
    final CurrencyRepository currencyRepository;




    // CREATE
    public Result addInput(InputDto dto) {

        Optional<Warehouse> warehouse = warehouseRepository.findById(dto.warehouseId());
        if (warehouse.isEmpty()) return new Result("Warehouse not found", false);

        Optional<Supplier> supplier = supplierRepository.findById(dto.supplierId());
        if (supplier.isEmpty()) return new Result("Supplier not found", false);

        Optional<Currency> currency = currencyRepository.findById(dto.currencyId());
        if (currency.isEmpty()) return new Result("Currency not found", false);

        Input input = new Input();
        input.setDate(LocalDate.from(LocalDateTime.now()));
        input.setWarehouse(warehouse.get());
        input.setSupplier(supplier.get());
        input.setCurrency(currency.get());
        input.setFactureNumber(dto.factureNumber());
        input.setCode(generateCode());

        inputRepository.save(input);
        return new Result("Input saved", true);
    }
    public String generateCode() {
        Integer maxCode = inputRepository.getMaxCode();
        if (maxCode == null) return "1";
        return String.valueOf(maxCode + 1);
    }

    // READ ALL
    public List<Input> getAllInputs() {
        return inputRepository.findAll();
    }

    // READ BY ID
    public Optional<Input> getInputById(Integer id) {
        return inputRepository.findById(id);
    }

    // UPDATE
    public Result updateInput(Integer id, InputDto dto) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) return new Result("Input not found", false);

        Input input = optionalInput.get();

        Optional<Warehouse> warehouse = warehouseRepository.findById(dto.warehouseId());
        if (warehouse.isEmpty()) return new Result("Warehouse not found", false);

        Optional<Supplier> supplier = supplierRepository.findById(dto.supplierId());
        if (supplier.isEmpty()) return new Result("Supplier not found", false);

        Optional<Currency> currency = currencyRepository.findById(dto.currencyId());
        if (currency.isEmpty()) return new Result("Currency not found", false);

        input.setDate(LocalDate.from(LocalDateTime.now()));
        input.setWarehouse(warehouse.get());
        input.setSupplier(supplier.get());
        input.setCurrency(currency.get());
        input.setFactureNumber(dto.factureNumber());
        input.setCode(generateCode());

        inputRepository.save(input);
        return new Result("Input updated", true);
    }

    // DELETE
    public Result deleteInput(Integer id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return new Result("Input not found", false);
        }
        inputRepository.deleteById(id);
        return new Result("Input deleted", true);
    }
}

