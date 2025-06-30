package com.portfolio.community;

import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import com.portfolio.community.contents.command.domain.post.Post;
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

//        Category testCategory = categoryRepository.save(new Category("자유 게시판","누구나 자유롭게 이야기를 나눌 수 있는 공간입니다.
//일상, 고민, 유머, 정보 공유 등 주제에 제한 없이 편하게 글을 작성해 주세요.
//서로를 존중하는 따뜻한 소통을 기대합니다."));


    }
}
