package com.pccg.pccguser.infrastructure.common.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    /*
     * mock mq
     */
    @Autowired
    private ApplicationContext applicationContext;

    public void publish(DomainEvent domainEvent) {
        applicationContext.publishEvent(domainEvent);
    }
}
