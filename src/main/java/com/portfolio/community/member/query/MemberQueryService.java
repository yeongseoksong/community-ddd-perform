package com.portfolio.community.member.query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberDataDao memberDataDao;

    public MemberData getMemberData(String memberId) {
        MemberData memberData = memberDataDao.findById(memberId).orElseThrow(NoMemberException::new);
        return memberData;
    }

}
