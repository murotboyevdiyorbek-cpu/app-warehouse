package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.ProductDto;
import uz.diyor.appwarehouse.dto.Result;

import uz.diyor.appwarehouse.entity.Product;
import uz.diyor.appwarehouse.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    final ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Result updateProduct(@PathVariable Integer id,
                                @RequestBody ProductDto productDto) {
        return productService.updateProduct(id,productDto);
    }




}
