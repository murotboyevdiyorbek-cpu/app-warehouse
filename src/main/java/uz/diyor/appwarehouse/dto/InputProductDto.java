package uz.diyor.appwarehouse.dto;

import java.time.LocalDate;

public record InputProductDto(

        Integer productId,
        Double amount,
        Double price,
        LocalDate expiryDate,
        Integer inputId
) {
}
