package com.portfolio.community.contents.command.presentation;


import com.portfolio.community.common.response.Resp;
import com.portfolio.community.contents.command.application.category.CreateCategoryRequest;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryCommandController {

    private final CategoryRepository categoryRepository;

    @PostMapping
    public Resp<Category> create(@Valid @RequestBody CreateCategoryRequest dto) {
        Category category = new Category(dto.getName());
        return Resp.ok(categoryRepository.save(category));
    }

    @GetMapping("/{id}")
    public Resp<Category> getById(@PathVariable String id) {
        Category category = categoryRepository.findById(CategoryId.of(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        return Resp.ok(category);
    }

    @GetMapping
    public Resp<List<Category>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return Resp.ok(categories);
    }

    @PutMapping("/{id}")
    public Resp<Category> update(@PathVariable String id, @RequestBody Map<String, String> body) {
        String name = body.get("name");
        Category category = categoryRepository.findById(CategoryId.of(id))
                .orElseThrow(() -> new EntityNotFoundException("Category not found: " + id));
        Category updated = Category.of( CategoryId.of(id),name);
        Category saved = categoryRepository.save(updated);
        return Resp.ok(saved);
    }

    @DeleteMapping("/{id}")
    public Resp<Void> delete(@PathVariable String id) {
        categoryRepository.deleteById(CategoryId.of(id));
        return Resp.ok();
    }

}
