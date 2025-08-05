package com.portfolio.community.subscribe.subscribe.application;


import com.portfolio.community.common.entity.Period;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.product.GetProductService;
import com.portfolio.community.product.Product;
import com.portfolio.community.subscribe.order.domain.Order;
import com.portfolio.community.subscribe.order.domain.Orderer;
import com.portfolio.community.subscribe.subscribe.domain.Subscribe;
import com.portfolio.community.subscribe.subscribe.domain.SubscribeRepository;
import com.portfolio.community.subscribe.subscribe.domain.SubscriberId;
import com.portfolio.community.subscribe.subscribe.domain.SubscriberInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final GetProductService getProductService;

    @Transactional
    public Subscribe createByOrder(Order order) {
        if(!order.isApproved())
            throw new IllegalArgumentException("Order is not approved");

        Product product = getProductService.getById(order.getOrderItem().getProductId());
        Period period = product.calcLocalDate(LocalDateTime.now());
        SubscriberInfo subscriberInfo = new SubscriberInfo(
                new SubscriberId(order.getOrderer().getMemberId()), order.getOrderer().getName());

        Subscribe subscribe = new Subscribe(subscriberInfo, period);
        return subscribeRepository.save(subscribe);
    }
}
