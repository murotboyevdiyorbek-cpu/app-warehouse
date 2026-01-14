package uz.diyor.appwarehouse.dto;


import java.sql.Timestamp;

public record InputDto(
        Timestamp date,
        Integer warehouseId,
        Integer supplierId,
        Integer currencyId,
        String factureNumber
) {}
