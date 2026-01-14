package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.InputProductDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Input;
import uz.diyor.appwarehouse.entity.InputProduct;
import uz.diyor.appwarehouse.entity.Product;
import uz.diyor.appwarehouse.repository.InputProductRepository;
import uz.diyor.appwarehouse.repository.InputRepository;
import uz.diyor.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InputProductService {

    final InputRepository inputRepository;
    final ProductRepository productRepository;
    final InputProductRepository inputProductRepository;


    public Result addInputProduct(InputProductDto dto) {

        Optional<Product> product = productRepository.findById(dto.productId());
        if (product.isEmpty()) return new Result("Product not found", false);

        Optional<Input> input = inputRepository.findById(dto.inputId());
        if (input.isEmpty()) return new Result("Input not found", false);

        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(product.get());
        inputProduct.setAmount(dto.amount());
        inputProduct.setPrice(dto.price());
        inputProduct.setExpiryDate(dto.expiryDate());
        inputProduct.setInput(input.get());

        inputProductRepository.save(inputProduct);
        return new Result("InputProduct saved", true);
    }


    public List<InputProduct> getAllInputProducts() {
        return inputProductRepository.findAll();
    }

    public Result deleteById(Integer id) {
        Optional<InputProduct> inputProduct = inputProductRepository.findById(id);
        if (inputProduct.isEmpty()) return new Result("Input Product not found", false);
        inputProductRepository.deleteById(id);
        return new Result("Input Product deleted", true);
    }

    public Result updateById(Integer id, InputProductDto dto) {

            Optional<InputProduct> optionalInputProduct =
                    inputProductRepository.findById(id);

            if (optionalInputProduct.isEmpty()) {
                return new Result("InputProduct not found", false);
            }

            Optional<Product> product =
                    productRepository.findById(dto.productId());
            if (product.isEmpty()) {
                return new Result("Product not found", false);
            }

            Optional<Input> input =
                    inputRepository.findById(dto.inputId());
            if (input.isEmpty()) {
                return new Result("Input not found", false);
            }

            InputProduct inputProduct = optionalInputProduct.get();

            inputProduct.setProduct(product.get());
            inputProduct.setAmount(dto.amount());
            inputProduct.setPrice(dto.price());
            inputProduct.setExpiryDate(dto.expiryDate());
            inputProduct.setInput(input.get());

            inputProductRepository.save(inputProduct);

            return new Result("InputProduct updated", true);


    }
}
