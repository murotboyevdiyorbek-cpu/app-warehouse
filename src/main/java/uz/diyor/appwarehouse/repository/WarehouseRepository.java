package uz.diyor.appwarehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);

}
