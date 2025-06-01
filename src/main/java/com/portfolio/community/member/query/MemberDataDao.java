package com.portfolio.community.member.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberDataDao extends JpaRepository<MemberData,String> {

    Optional<MemberData> findById(String memberId);
}
