package com.portfolio.community.subscribe.order.application;


import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.OrderId;
import com.portfolio.community.subscribe.order.domain.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GetOrderService {
    private final OrderRepository orderRepository;

    public Order getById(OrderId orderId){
        return orderRepository.findById(orderId).orElseThrow(()->new EntityNotFoundException("Order with id " + orderId + " not found"));
    }


    //TODO 인증 연동
    public Order getByIdIfPermitted(OrderId orderId){
        return this.getById(orderId);
    }
}
