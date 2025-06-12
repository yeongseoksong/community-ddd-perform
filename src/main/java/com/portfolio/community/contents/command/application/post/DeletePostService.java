package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.contents.command.domain.post.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DeletePostService {

    private final PostRepository postRepository;
    private final GetPostService getPostService;

    public void deletePostByIdFromAuthor(Author author,PostId id){
        Post post = getPostService.getByIdFromAuthor(author,id);
        postRepository.delete(post);
    }
}
