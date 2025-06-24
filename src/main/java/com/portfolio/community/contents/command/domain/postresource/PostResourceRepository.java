package com.portfolio.community.contents.command.domain.postresource;

import com.portfolio.community.contents.command.domain.post.PostId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostResourceRepository extends JpaRepository<PostResource,Long> {


    List<PostResource> findByPostId(PostId postId);
}
