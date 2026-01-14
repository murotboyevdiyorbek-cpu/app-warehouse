package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.diyor.appwarehouse.entity.User;

public interface UserRepository   extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumber(String phone);

    @Query("SELECT MAX(p.code) FROM Product p")
    Integer getMaxCode();
}

