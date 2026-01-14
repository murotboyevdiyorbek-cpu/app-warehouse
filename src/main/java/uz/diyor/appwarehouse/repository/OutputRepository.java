package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.diyor.appwarehouse.entity.Output;

public interface OutputRepository extends JpaRepository<Output,Integer> {

    @Query("SELECT MAX(p.code) FROM Product p")
    Integer getMaxCode();

}
