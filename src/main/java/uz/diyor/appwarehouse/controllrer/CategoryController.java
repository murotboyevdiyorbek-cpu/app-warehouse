package uz.diyor.appwarehouse.controllrer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.diyor.appwarehouse.dto.CategoryDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Category;
import uz.diyor.appwarehouse.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    final CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    public List<Category> getAllCategory() {
       return categoryService.getAllCategory();
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable int id) {
        return categoryService.deleteByIdCategory(id);
    }

    @PutMapping("/{id}")
    public Result updateCategory(
            @PathVariable int id,
            @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategoryById(id,categoryDto);
    }







}
