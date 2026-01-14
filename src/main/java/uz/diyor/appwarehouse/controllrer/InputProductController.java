package uz.diyor.appwarehouse.controllrer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.InputProductDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.InputProduct;
import uz.diyor.appwarehouse.service.InputProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inputProduct")
public class InputProductController {

    final InputProductService inputProductService;

    @PostMapping
    public Result addInputProduct(@RequestBody InputProductDto dto) {
       return inputProductService.addInputProduct(dto);
    }

    @GetMapping
    public List<InputProduct> getInputProducts() {
        return inputProductService.getAllInputProducts();
    }

    @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id) {
        return inputProductService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Result updateInputProduct(@PathVariable Integer id,
                                     @RequestBody InputProductDto dto) {
        return inputProductService.updateById(id,dto);

    }



}
