package uz.diyor.appwarehouse.dto;

import uz.diyor.appwarehouse.entity.Warehouse;

import java.util.Set;

public record UserDto(
        String firstName,

        String lastName,

        String phoneNumber,

        String password,

        Set<Warehouse> warehouses
) {
}
