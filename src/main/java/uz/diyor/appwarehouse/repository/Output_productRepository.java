package uz.diyor.appwarehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.diyor.appwarehouse.entity.Output;
import uz.diyor.appwarehouse.entity.OutputProduct;

public interface Output_productRepository  extends JpaRepository<OutputProduct,Integer> {

}
