package com.portfolio.community.contents.command.ui;


import com.portfolio.community.contents.command.application.category.CreateCategoryRequest;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryCommandController {

    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody CreateCategoryRequest dto) {
        Category category = new Category(dto.getName());
        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable String id) {
        Category category = categoryRepository.findById(CategoryId.of(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Map<String, String> body) {
        String name = body.get("name");
        Category category = categoryRepository.findById(CategoryId.of(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        Category updated = Category.of( CategoryId.of(id),name);
        Category saved = categoryRepository.save(updated);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryRepository.deleteById(CategoryId.of(id));
        return ResponseEntity.noContent().build();
    }

}
