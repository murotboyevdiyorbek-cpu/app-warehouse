package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.ProductDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Attachment;
import uz.diyor.appwarehouse.entity.Category;
import uz.diyor.appwarehouse.entity.Measurement;
import uz.diyor.appwarehouse.entity.Product;
import uz.diyor.appwarehouse.repository.AttachmentRepository;
import uz.diyor.appwarehouse.repository.CategoryRepository;
import uz.diyor.appwarehouse.repository.MeasurementRepository;
import uz.diyor.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final AttachmentRepository attachmentRepository;
    final MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {
        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.name(), productDto.categoryId());
        if (existsByNameAndCategoryId) {
            return  new Result("Bunday mahsut kategoriyada mavjut ",false);
        }
        Optional<Category> category = categoryRepository.findById(productDto.categoryId());
        if (category.isEmpty()) {
            return  new Result("Bunday kategoriyada mavjut emas",false);
        }
        Optional<Attachment> attachment= attachmentRepository.findById(productDto.photoID());
        if (attachment.isEmpty()) {
            return  new Result("Bunday photo mavjut emas",false);
        }
        Optional<Measurement> measurement = measurementRepository.findById(productDto.measurementID());
        if (measurement.isEmpty()) {
            return  new Result("Bunday o'lchov birligi mavjut emas",false);
        }


        Product product = new Product();
        product.setName(productDto.name());
        product.setCode(generateCode());
        product.setCategory(category.get());
        product.setPhoto(attachment.get());
        product.setMeasurement(measurement.get());
        productRepository.save(product);
        return new Result("Masulot saqlandi ",true);

    }

    public String generateCode() {
        Integer maxCode = productRepository.getMaxCode();
        if (maxCode == null) return "1";
        return String.valueOf(maxCode + 1);
    }


    public List<Product> getAllProducts() {
       return productRepository.findAll();
    }

    public Result deleteProduct(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return new Result("Product not found with id: " + id, false);
        }
        productRepository.deleteById(id);
        return new Result("Product deleted",true);
    }

    public Result updateProduct(Integer id, ProductDto productDto) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Product not found with id: " + id, false);
        }
        Product product = optional.get();
        product.setName(productDto.name());
        product.setCode(generateCode());
        product.setCategory(categoryRepository.findById(productDto.categoryId()).get());
        product.setPhoto(attachmentRepository.findById(productDto.photoID()).get());
        product.setMeasurement(measurementRepository.findById(productDto.measurementID()).get());
        productRepository.save(product);
        return new Result("Product updated",true);
    }
}
