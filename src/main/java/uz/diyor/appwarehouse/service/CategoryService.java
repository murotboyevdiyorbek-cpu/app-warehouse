package uz.diyor.appwarehouse.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.diyor.appwarehouse.dto.CategoryDto;
import uz.diyor.appwarehouse.dto.Result;
import uz.diyor.appwarehouse.entity.Category;
import uz.diyor.appwarehouse.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.name());
        if (categoryDto.parentCategoryId() != null) {
            Optional<Category> parentCategory = categoryRepository.findById(categoryDto.parentCategoryId());
            if (parentCategory.isEmpty())
                return new Result("Category not found ",false);
            category.setParentCategory(parentCategory.get());
        }
        categoryRepository.save(category);
        return new Result("Category added",true);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Result deleteByIdCategory(int id) {
        if(!categoryRepository.existsById(id)) {
            new Result("Category not found with id: " + id, false);
        }
        categoryRepository.deleteById(id);
        return new Result("Category deleted",true);
    }

    public Result updateCategoryById(int id, CategoryDto categoryDto) {
        if(!categoryRepository.existsById(id)) {
            new Result("Category not found with id: " + id, false);
        }
        Category category = new Category();
        category.setName(categoryDto.name());
        if (categoryDto.parentCategoryId() != null) {
            Optional<Category> parentCategory = categoryRepository.findById(categoryDto.parentCategoryId());
        }
        category.setId(id);
        categoryRepository.save(category);
        return new Result("Category updated",true);

    }
}
