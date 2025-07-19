package com.portfolio.community.subscribe.application;


import com.portfolio.community.product.GetProductService;
import com.portfolio.community.product.Product;
import com.portfolio.community.subscribe.domain.order.Order;
import com.portfolio.community.subscribe.domain.order.OrderItem;
import com.portfolio.community.subscribe.domain.order.OrderRepository;
import com.portfolio.community.subscribe.domain.order.Orderer;
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
