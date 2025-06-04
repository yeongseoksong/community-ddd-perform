package com.portfolio.community;

import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestData implements CommandLineRunner {
    private final CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {

        Category testCategory = categoryRepository.save(new Category(new CategoryId("category"),"자유 게시판"));

    }
}
