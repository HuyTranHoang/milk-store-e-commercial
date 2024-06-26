package com.huy.mse.category;

import jakarta.persistence.NoResultException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id) {
        CategoryDto categoryDto = categoryService.getCategoryById(id);

        if (categoryDto == null) {
            throw new NoResultException("Category not found");
        }

        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryReturn = categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(categoryReturn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        CategoryDto categoryReturn = categoryService.updateCategory(categoryDto);

        if (categoryReturn == null) {
            throw new NoResultException("Category not found");
        }

        return ResponseEntity.ok(categoryReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
