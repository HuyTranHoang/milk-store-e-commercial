package com.huy.mse.controller;

import com.huy.mse.dto.CategoryDto;
import com.huy.mse.service.CategoryService;
import jakarta.validation.Valid;
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
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable long id) {
        CategoryDto categoryDto = categoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid CategoryDto categoryDto) {
        CategoryDto categoryReturn = categoryService.create(categoryDto);
        return ResponseEntity.ok(categoryReturn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        CategoryDto categoryReturn = categoryService.update(id, categoryDto);
        return ResponseEntity.ok(categoryReturn);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
