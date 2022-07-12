package com.pccg.pccguser.domain.event;

import java.util.Date;

import com.pccg.pccguser.infrastructure.common.event.DomainEvent;
import com.pccg.pccguser.infrastructure.common.event.EventType;
import lombok.Data;

@Data
public class UserCreateEvent extends DomainEvent {

    public static UserCreateEvent make(String userName, long userId) {
        UserCreateEvent event = new UserCreateEvent();
        event.setId(String.valueOf(userId));
        event.setTimestamp(new Date());
        event.setType(EventType.UserCreate);
        event.setSource(userName);
        return event;
    }
}
