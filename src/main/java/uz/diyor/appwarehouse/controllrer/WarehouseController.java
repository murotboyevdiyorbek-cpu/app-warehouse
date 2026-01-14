package uz.diyor.appwarehouse.controllrer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Warehouse;
import uz.diyor.appwarehouse.service.WarehouseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/warehouse")
public class WarehouseController {

    final WarehouseService warehouseService;

    @PostMapping
    public Result add(@RequestBody  Warehouse warehouse) {
        return warehouseService.addwarehouse(warehouse);
    }
    @GetMapping
    public List<Warehouse> getAll() {
        return warehouseService.getAllWarehouse();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
       return warehouseService.deteleWarehouse(id);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id,
                         @RequestBody Warehouse warehouse) {
        return warehouseService.updateWarehouse(id,warehouse);
    }




}
