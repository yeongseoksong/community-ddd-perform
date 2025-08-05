package com.portfolio.community.subscribe.order.application;

import com.portfolio.community.common.entity.Won;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.product.ProductId;
import com.portfolio.community.subscribe.order.domain.*;
import com.portfolio.community.subscribe.payment.domain.PaymentState;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusServiceUnitTest {
    private  Order order;
    private  ConfirmPaymentResponse successResp;
    private  ConfirmPaymentResponse failResp;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private UpdateOrderStatusService updateOrderStatusService;

    @BeforeEach
    void setUp() {
        this.order= new Order(new OrderItem(new ProductId(), "subscribe", new Won(1000L)), new Orderer(new MemberId(), "tester"));
        this.successResp=new ConfirmPaymentResponse(order.getId().
                getValue(),"key",
                order.getOrderItem().getAmount().getValue(),
                PaymentState.DONE,
                OffsetDateTime.now(),
                OffsetDateTime.now());
        this.failResp=new ConfirmPaymentResponse(order.getId().
                getValue(),"key",
                order.getOrderItem().getAmount().getValue(),
                PaymentState.ABORTED,
                OffsetDateTime.now(),
                OffsetDateTime.now());

        when(orderRepository.save(order)).thenReturn(order);
    }

    @Test
    public void 응답_성공시_주문은_승인상태로_변경된다(){
        Order order1 = updateOrderStatusService.updateStatus(successResp, order);
        Assertions.assertThat(order1.getState()).isEqualTo(OrderState.APPROVED);
    }

    @Test
    public void 응답_실패시_주문은_승인상태로_변경된다(){
        Order order1 = updateOrderStatusService.updateStatus(failResp, order);
        Assertions.assertThat(order1.getState()).isEqualTo(OrderState.FAIL);
    }
}