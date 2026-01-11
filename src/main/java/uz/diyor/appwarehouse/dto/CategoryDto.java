package uz.diyor.appwarehouse.dto;

public record CategoryDto(
        String name,
        Integer parentCategoryId
) {
}
