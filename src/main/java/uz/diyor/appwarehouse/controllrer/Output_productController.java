package uz.diyor.appwarehouse.controllrer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.Output_productDto;
import uz.diyor.appwarehouse.dto.Result;

import uz.diyor.appwarehouse.entity.OutputProduct;
import uz.diyor.appwarehouse.service.Output_productService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/output_product")
public class Output_productController {

    final Output_productService output_productService;

    @PostMapping
    public Result addOutput_product(@RequestBody Output_productDto dto) {
        return output_productService.addOutput_product(dto);
    }

    @GetMapping
    public List<OutputProduct> getOutput_product() {
        return output_productService.getAllOutputProduct();
    }

    @DeleteMapping("/id")
    public Result deleteOutput_product(@PathVariable Integer id) {
        return output_productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Result updateOutput_product(
            @PathVariable Integer id,
            @RequestBody Output_productDto dto
    ) {
        return output_productService.updateById(id, dto);
    }







}
