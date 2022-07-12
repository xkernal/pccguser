package com.pccg.pccguser.domain.event;

import com.pccg.pccguser.infrastructure.common.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 用户领域时间监听处理
 */
@Slf4j
@Component
public class UserEventListener {

    /**
     * mock send email
     * @param userCreateEvent
     */
    @EventListener
    public void listen(UserCreateEvent userCreateEvent) {
        log.info("mock send mail welcome {} join us", userCreateEvent.getSource());
    }
}
