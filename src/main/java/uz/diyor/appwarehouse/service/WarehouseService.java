package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Warehouse;
import uz.diyor.appwarehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    final WarehouseRepository warehouseRepository;

    public Result addwarehouse(Warehouse warehouse) {
        warehouseRepository.existsByName(warehouse.getName());
        if (warehouseRepository.existsByName(warehouse.getName())) {
            return new Result("Warehouse already exists",false);
        }
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added",true);
    }

    public List<Warehouse> getAllWarehouse() {
        return warehouseRepository.findAll();
    }

    public Result deteleWarehouse(Integer id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if(warehouse.isEmpty()) {
            return new Result("Warehouse not found",false);
        }
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted",true);
    }


    public Result updateWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if(warehouseOptional.isEmpty()) {
            return new Result("Warehouse not found",false);
        }
        Warehouse warehouseToUpdate = warehouseOptional.get();
        warehouseToUpdate.setName(warehouse.getName());
        warehouseRepository.save(warehouseToUpdate);
        return new Result("Warehouse updated",true);
    }
}
