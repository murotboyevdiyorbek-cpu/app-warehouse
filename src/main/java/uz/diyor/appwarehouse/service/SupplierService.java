package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Supplier;
import uz.diyor.appwarehouse.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    // CREATE
    public Result addSupplier(Supplier supplier) {

        if (supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())) {
            return new Result("Supplier phone number already exists", false);
        }

        supplierRepository.save(supplier);
        return new Result("Supplier saved", true);
    }

    // READ ALL
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // READ BY ID
    public Supplier getSupplierById(Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Result updateSupplier(Integer id, Supplier supplier) {

        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Supplier not found", false);
        }

        Supplier existing = optional.get();


        if (!existing.getPhoneNumber().equals(supplier.getPhoneNumber())
                && supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber())) {
            return new Result("Phone number already exists", false);
        }

        existing.setName(supplier.getName());   // AbsEntity’dan
        existing.setPhoneNumber(supplier.getPhoneNumber());
        existing.setActive(supplier.isActive());

        supplierRepository.save(existing);
        return new Result("Supplier updated", true);
    }

    // DELETE
    public Result deleteSupplier(Integer id) {

        Optional<Supplier> optional = supplierRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Supplier not found", false);
        }

        supplierRepository.deleteById(id);
        return new Result("Supplier deleted", true);
    }
}

