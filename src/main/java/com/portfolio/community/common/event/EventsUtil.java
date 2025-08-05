package com.portfolio.community.common.event;

import org.springframework.context.ApplicationEventPublisher;

public class EventsUtil {
    public static ApplicationEventPublisher pub;

    static void setPublisher(ApplicationEventPublisher publisher) {
        pub = publisher;
    }

    public static void raise(Object event) {
        if (pub != null) {
            pub.publishEvent(event);
        }
    }
}
