package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.member.command.domain.MemberId;

public interface AuthorService {
    Author createAuthor(MemberId memberId);
}
