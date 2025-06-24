package com.portfolio.community.contents.query;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostQueryService {

    private final PostMapper postMapper;

    public List<PostSummaryVO> fetchPostsByCategory(String categoryId, Pageable pageable) {
        int pageSize=pageable.getPageSize();
        int offset = (int)pageable.getOffset();
        Sort sort = pageable.getSort();


        List<PostSummaryVO> content;
        if (sort.isSorted()) {
            Sort.Order order = sort.iterator().next();
            String sortField = order.getProperty();
            String sortDirection = order.getDirection().name().toLowerCase();

            if(!Set.of("created_at","like_count").contains(sortField)){
                throw new IllegalStateException();
            }

            content = postMapper.getPostListByCategoryId(categoryId,pageSize,offset,sortField,sortDirection);
        }
        else{
            content = postMapper.getPostListByCategoryId(categoryId,pageSize,offset,null,null);
        }

        return content;
    }
}
