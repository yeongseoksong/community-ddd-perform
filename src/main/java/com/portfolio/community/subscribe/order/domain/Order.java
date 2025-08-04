package com.portfolio.community.subscribe.order.domain;

import com.portfolio.community.common.entity.BaseEntity;
import com.portfolio.community.common.entity.Won;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;
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

    public boolean validatePaymentRequest(OrderId orderId, Won amount){
        if(this.id.equals(orderId)&& this.orderItem.getAmount().equals(amount))
            return true;
        else throw new IllegalArgumentException("orderItem and amount are not equal");
    }

    public void changeState( ConfirmPaymentResponse confirmPaymentResponse ){
        if(!(this.state.equals(OrderState.PENDING)))
            throw new IllegalStateException("Order cannot be approved");

        if(confirmPaymentResponse.getStatus().isSuccess()) this.state=OrderState.APPROVED;
        else this.state=OrderState.FAIL;

    }

}


