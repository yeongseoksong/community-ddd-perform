package com.portfolio.community.subscribe.domain.order;

import com.portfolio.community.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="`order`")
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
public class Order extends BaseEntity {

    @EmbeddedId
    private OrderId id;

    @Embedded
    private OrderItem orderItem;

    @Embedded
    private Orderer orderer;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    public Order(OrderItem orderItem,Orderer orderer) {
        if(orderItem == null || orderer==null) {
            throw new IllegalArgumentException("orderItem cannot be null");
        }

        this.id = new OrderId();
        this.orderer= orderer;
        this.orderItem = orderItem;
        this.state = OrderState.PENDING;
    }
}


