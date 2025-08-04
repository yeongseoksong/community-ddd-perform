package com.portfolio.community.subscribe.payment.application;


import com.portfolio.community.common.entity.Won;
import com.portfolio.community.subscribe.order.application.GetOrderService;
import com.portfolio.community.subscribe.order.application.UpdateOrderStatusService;
import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.OrderId;
import com.portfolio.community.subscribe.payment.domain.PaymentClient;
import com.portfolio.community.subscribe.payment.domain.PaymentRepository;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentService {


    private final GetOrderService getOrderService;
    private final UpdateOrderStatusService updateOrderStatusService;
    private final PaymentRepository paymentRepository;
    private final PaymentClient paymentClient;



    public Order success(OrderId orderId,  String paymentKey, Long amount){
        // 1. 주문 정보 조회
        Order order = getOrderService.getByIdIfPermitted(orderId);
        if(order.getState().getIsAccessible())
            return order;
        // 2. 주문 검증
        order.validatePaymentRequest(orderId,new Won(amount));

        // 3. 결제 서버 확인
        ConfirmPaymentResponse confirmPaymentResponse = paymentClient.confirm(orderId, paymentKey, amount);
        paymentRepository.save(confirmPaymentResponse.toEntity());

        // 4. 주문 상태 변경
        updateOrderStatusService.updateStatus(confirmPaymentResponse,order);

        return order;
    }

    public void fail(){

    }
}
