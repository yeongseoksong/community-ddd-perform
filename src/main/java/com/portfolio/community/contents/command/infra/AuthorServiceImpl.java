package com.portfolio.community.contents.command.infra;

import com.portfolio.community.member.query.MemberData;
import com.portfolio.community.member.query.MemberQueryService;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.AuthorService;
import com.portfolio.community.member.query.MemberData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl  implements AuthorService {

    private final MemberQueryService memberQueryService;

    @Override
    public Author createAuthor(MemberId authorMemberId) {
        MemberData memberData = memberQueryService.getMemberData(authorMemberId.getValue());
        return new Author(MemberId.of(memberData.getId()),memberData.getName());
    }
}
