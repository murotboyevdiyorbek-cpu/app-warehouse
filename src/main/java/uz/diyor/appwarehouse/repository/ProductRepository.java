package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.diyor.appwarehouse.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByNameAndCategoryId(String productName, Integer categoryId);

    @Query("SELECT MAX(p.code) FROM Product p")
    Integer getMaxCode();
}
