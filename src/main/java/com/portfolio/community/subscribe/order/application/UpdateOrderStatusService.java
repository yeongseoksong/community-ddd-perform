package com.portfolio.community.subscribe.order.application;


import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.OrderRepository;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order updateStatus(ConfirmPaymentResponse confirmPaymentResponse, Order order ){
        order.changeState(confirmPaymentResponse);
        return orderRepository.save(order);
    }
}
