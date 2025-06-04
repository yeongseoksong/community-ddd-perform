package com.portfolio.community.contents;

import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostContent;
import com.portfolio.community.member.command.domain.MemberId;

public class PostFactory {


    public static Post generate(String authorId,String authorName, String title, String content,String categoryid_)
    {
        MemberId testMember = MemberId.of(authorId);
        Author author = new Author(testMember,authorName);
        PostContent postContent = new PostContent(title,content);
        CategoryId categoryId = new CategoryId(categoryid_);
        Post post = new Post(author, postContent, categoryId,false);

        return post;
    }
}
