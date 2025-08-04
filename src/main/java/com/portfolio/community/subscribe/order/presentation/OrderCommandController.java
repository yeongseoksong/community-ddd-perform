package com.portfolio.community.subscribe.order.presentation;


import com.portfolio.community.common.response.Resp;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.subscribe.order.application.CreateOrderService;
import com.portfolio.community.subscribe.order.application.InitOrderRequest;
import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.Orderer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Order",description = "구독 주문 API")
public class OrderCommandController {

    private final CreateOrderService createOrderService;

    //TODO security 로 변경
    final Orderer orderer =new Orderer(MemberId.of("test"), "test");


    @PostMapping("/api/member/orders")
    public Resp<Order> initOrder(@RequestBody InitOrderRequest initOrderRequest
                                 ) {
        return Resp.ok(createOrderService.initOrder(initOrderRequest,orderer));
    }

//    @PostMapping("/api/member/orders/{orderId}")
//    public Resp<Order> updateOrderAfterPayments(@PathVariable OrderId orderId) {
//
//        return Resp.ok(createOrderService.initOrder(initOrderRequest,orderer));
//
//    }

}
