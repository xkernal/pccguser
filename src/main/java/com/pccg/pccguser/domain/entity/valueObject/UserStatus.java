package com.pccg.pccguser.domain.entity.valueObject;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

    Enable(1),
    Disable(0);
    private int code;
    UserStatus(int code) {
        this.code = code;
    }

    private static Map<Integer, UserStatus> map = new HashMap<>();
    static {
        for(UserStatus status : UserStatus.values()) {
            map.put(status.getCode(), status);
        }
    }

    public static UserStatus findByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }
}
