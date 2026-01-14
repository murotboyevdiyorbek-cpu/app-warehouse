package uz.diyor.appwarehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.diyor.appwarehouse.entity.Input;

@Repository
public interface InputRepository extends JpaRepository<Input, Integer> {


    @Query("SELECT MAX(p.code) FROM Product p")
    Integer getMaxCode();
}
