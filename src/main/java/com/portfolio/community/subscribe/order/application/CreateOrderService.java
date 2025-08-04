package com.portfolio.community.subscribe.order.application;


import com.portfolio.community.product.GetProductService;
import com.portfolio.community.product.Product;
import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.OrderItem;
import com.portfolio.community.subscribe.order.domain.OrderRepository;
import com.portfolio.community.subscribe.order.domain.Orderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final OrderRepository orderRepository;
    private final GetProductService getProductService;

    public Order initOrder(InitOrderRequest initOrderRequest, Orderer orderer){
        Product product = getProductService.getById(initOrderRequest.getProductId());
        OrderItem orderItem = new OrderItem(product.getId(), product.getName(), product.getPrice());
        Order order = new Order(orderItem, orderer);
        return orderRepository.save(order);
    }

}
