package uz.diyor.appwarehouse.dto;

public record ProductDto(
        String name,
        Integer categoryId,
        Integer photoID,
        Integer measurementID

) {
}
