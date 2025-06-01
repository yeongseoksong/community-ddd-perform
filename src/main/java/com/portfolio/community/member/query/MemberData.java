package com.portfolio.community.member.query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberData {

    @Id
    @Column(name="member_id")
    private String id;

    @Column(name = "name" )
    private String name;

    public MemberData(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
