package com.portfolio.community.member.command.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberId> {
}