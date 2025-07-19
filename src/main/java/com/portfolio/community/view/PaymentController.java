package com.portfolio.community.view;


import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.product.ProductId;
import com.portfolio.community.subscribe.application.CreateOrderService;
import com.portfolio.community.subscribe.application.InitOrderRequest;
import com.portfolio.community.subscribe.domain.order.Order;
import com.portfolio.community.subscribe.domain.order.OrderId;
import com.portfolio.community.subscribe.domain.order.OrderRepository;
import com.portfolio.community.subscribe.domain.order.Orderer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PaymentController {

    private final CreateOrderService createOrderService;

    private final OrderRepository orderRepository;
    //TODO security 로 변경
    final Orderer orderer =new Orderer(MemberId.of("test"), "test");
    
//http://localhost:8080/payments/toss/success?orderId=z9OJUhbvXNAuchTx

    @GetMapping("payments/toss")
    public String tossPaymentInitOrder(@RequestParam ProductId productId, Model model) {
        Order order = createOrderService.initOrder(new InitOrderRequest(productId), orderer);
        model.addAttribute("order", order);
        return "pages/payment/toss";
    }

//    paymentType=NORMAL
//    &orderId=n2QeWlHu6I5Ljrkt&paymentKey=tgen_20250719214924SBTo2&amount=10000

    @GetMapping("payments/toss/success")
    public String tossPaymentSuccess(OrderId orderId, Model model) {
        Optional<Order> byId = orderRepository.findById(orderId);
        model.addAttribute("order",byId.get());
        return "pages/payment/tossSuccess";
    }


    @GetMapping("payments/toss/fail")
    public String tossPaymentFail(OrderId orderId, Model model) {
        Optional<Order> byId = orderRepository.findById(orderId);
        model.addAttribute("order",byId.get());

        return "pages/payment/tossFail";
    }



}
