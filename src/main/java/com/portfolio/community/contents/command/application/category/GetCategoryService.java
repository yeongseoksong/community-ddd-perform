package com.portfolio.community.contents.command.application.category;

import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryNotFoundException;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryService {
    private final CategoryRepository categoryRepository;

    public Category getById(CategoryId categoryId){
       return categoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new
       );
    }

    public void assertById(CategoryId categoryId){
        if( !categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();
    }


}
