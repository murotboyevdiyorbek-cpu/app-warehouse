package uz.diyor.appwarehouse.dto;

import uz.diyor.appwarehouse.entity.Output;
import uz.diyor.appwarehouse.entity.Product;




public record Output_productDto(
        Product product,
        Double amount,
        Double price,
        Output output
) {
}


