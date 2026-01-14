package uz.diyor.appwarehouse.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.Output_productDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Output;
import uz.diyor.appwarehouse.entity.OutputProduct;
import uz.diyor.appwarehouse.entity.Product;
import uz.diyor.appwarehouse.repository.OutputRepository;
import uz.diyor.appwarehouse.repository.Output_productRepository;
import uz.diyor.appwarehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Output_productService {


    final Output_productRepository output_productRepository;
    final ProductRepository productRepository;
    final OutputRepository outputRepository;

    public Result addOutput_product(Output_productDto dto) {
        Optional<Product> optionalProduct =
                productRepository.findById(dto.product().getId());
        if (optionalProduct.isEmpty()) {
            return new Result("Product not faund",true);
        }
        Optional<Output> optionalOutput =
                outputRepository.findById(dto.output().getId());
        if (optionalOutput.isEmpty()) {
            return new Result("Output not found", false);
        }

        if (dto.amount() == null || dto.amount() <= 0) {
            return new Result("Amount must be greater than 0", false);
        }

        OutputProduct product = new OutputProduct();
        product.setProduct(optionalProduct.get());
        product.setOutput(optionalOutput.get());
        product.setAmount(dto.amount());
        product.setPrice(dto.price());
        output_productRepository.save(product);
        return new Result("Product added", true);
    }


    public List<OutputProduct> getAllOutputProduct() {
        return output_productRepository.findAll();
    }


    public Result deleteById(Integer id) {
        Optional<OutputProduct> optional = output_productRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Output not found", false);
        }
        output_productRepository.deleteById(id);
        return new Result("Output deleted", true);
    }


    public Result updateById(Integer id, Output_productDto dto) {

        Optional<OutputProduct> optional =
                output_productRepository.findById(id);
        if (optional.isEmpty()) {
            return new Result("Output product not found", false);
        }

        Optional<Product> productOpt =
                productRepository.findById(dto.product().getId());
        if (productOpt.isEmpty()) {
            return new Result("Product not found", false);
        }

        Optional<Output> outputOpt =
                outputRepository.findById(dto.output().getId());
        if (outputOpt.isEmpty()) {
            return new Result("Output not found", false);
        }

        OutputProduct outputProduct = optional.get();
        outputProduct.setProduct(productOpt.get());
        outputProduct.setOutput(outputOpt.get());
        outputProduct.setAmount(dto.amount());
        outputProduct.setPrice(dto.price());

        output_productRepository.save(outputProduct);

        return new Result("Output product updated", true);
    }

}


