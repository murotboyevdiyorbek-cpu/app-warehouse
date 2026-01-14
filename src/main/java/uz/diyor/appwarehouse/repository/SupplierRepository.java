package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
