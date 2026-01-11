package uz.diyor.appwarehouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Measurement;

public interface  MeasurementRepository extends JpaRepository<Measurement,Integer> {

    boolean existsByName(String name);
}
