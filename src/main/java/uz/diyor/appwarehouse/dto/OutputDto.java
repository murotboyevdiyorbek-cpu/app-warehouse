package uz.diyor.appwarehouse.dto;


import uz.diyor.appwarehouse.entity.Currency;
import uz.diyor.appwarehouse.entity.Warehouse;

import java.security.Timestamp;

public record OutputDto(
                 Timestamp date,
                 Warehouse warehouse,
                 Currency currency,
                 String factureNumber

) {


}
