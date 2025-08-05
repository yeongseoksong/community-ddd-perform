package com.portfolio.community.subscribe.subscribe.application;


import com.portfolio.community.subscribe.subscribe.domain.CreateSubscribeEvent;
import com.portfolio.community.subscribe.subscribe.domain.Subscribe;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
@Slf4j
public class CreateSubscribeListener {
    private final SubscribeService subscribeService;


    @EventListener(CreateSubscribeEvent.class)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    @Async
    public void listen(CreateSubscribeEvent createSubscribeEvent) {
        Subscribe byOrder = subscribeService.createByOrder(createSubscribeEvent.getOrder());
        log.info("Subscribe order: {}", byOrder.getId().getValue());
    }
}
