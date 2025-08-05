package com.portfolio.community.subscribe.subscribe.domain;

import com.portfolio.community.common.event.Event;
import com.portfolio.community.subscribe.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CreateSubscribeEvent extends Event {

    private Order order;

}
