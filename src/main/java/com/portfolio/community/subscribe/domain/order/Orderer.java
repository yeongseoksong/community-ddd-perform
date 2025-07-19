package com.portfolio.community.subscribe.domain.order;


import com.portfolio.community.member.command.domain.MemberId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Orderer {
    @AttributeOverride(name="value",column = @Column(name="orderer_id"))
    private MemberId memberId;

    @Column(name = "orderer_name")
    private String name;

    public static Orderer of(MemberId memberId, String name){
        return new Orderer(memberId,name);
    }

    public Orderer(MemberId memberId, String name) {
        if(memberId==null || name==null) {
            throw new IllegalArgumentException( "memberId and name cannot be null" );
        }
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderer orderer = (Orderer) o;
        return Objects.equals(getMemberId(), orderer.getMemberId()) && Objects.equals(getName(), orderer.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getMemberId());
        result = 31 * result + Objects.hashCode(getName());
        return result;
    }
}
