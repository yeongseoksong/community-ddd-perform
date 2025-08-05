package com.portfolio.community.subscribe.payment.application;

import com.portfolio.community.common.entity.Won;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.product.ProductId;
import com.portfolio.community.subscribe.order.application.GetOrderService;
import com.portfolio.community.subscribe.order.application.UpdateOrderStatusService;
import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.OrderItem;
import com.portfolio.community.subscribe.order.domain.Orderer;
import com.portfolio.community.subscribe.payment.domain.PaymentClient;
import com.portfolio.community.subscribe.payment.domain.PaymentRepository;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PaymentServiceUnitTest {

    @Mock
    private GetOrderService getOrderService;

    @Mock
    private UpdateOrderStatusService updateOrderStatusService;


    private PaymentClient paymentClient = new PaymentClientMock();

    @Mock
    private PaymentRepository paymentRepository;

    private PaymentService  paymentService ;

    private  Order order ;

    private ConfirmPaymentResponse confirmPaymentResponse;

    @BeforeEach
    void setUp() {
        this.order=new Order(new OrderItem(new ProductId(), "subscribe", new Won(1000L)), new Orderer(new MemberId(), "tester"));
        this.confirmPaymentResponse=paymentClient.confirm(this.order.getId(),"payment-key",this.order.getOrderItem().getAmount().getValue());


        given(getOrderService.getByIdIfPermitted(order.getId())).willReturn(this.order);
        given(paymentRepository.save(confirmPaymentResponse.toEntity())).willReturn(confirmPaymentResponse.toEntity());


        paymentService = new PaymentService(
                getOrderService,
                updateOrderStatusService,
                paymentRepository,
                paymentClient
        );


    }


    @Test
    public void 주문_(){
        Order confirm = paymentService.confirm(order.getId(), "payment-key", order.getOrderItem().getAmount().getValue());


    }


    @Test
    public void 주문_확인_시에_요청금액을_검증한다(){
        org.assertj.core.api.Assertions.assertThatThrownBy(
                ()-> paymentService.confirm(order.getId(), "payment-key", 1l)
        ).isInstanceOf(IllegalArgumentException.class);
    }

}