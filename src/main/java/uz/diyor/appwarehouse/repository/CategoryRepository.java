package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {


}
