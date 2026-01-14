package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Supplier;
import uz.diyor.appwarehouse.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Integer id) {
        return supplierService.getSupplierById(id);
    }

    @PutMapping("/{id}")
    public Result updateSupplier(
            @PathVariable Integer id,
            @RequestBody Supplier supplier
    ) {
        return supplierService.updateSupplier(id, supplier);
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }
}


