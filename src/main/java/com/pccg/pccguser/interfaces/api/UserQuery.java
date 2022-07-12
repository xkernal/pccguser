package com.pccg.pccguser.interfaces.api;

import java.util.ArrayList;
import java.util.List;

public class UserQuery extends Page {
    private List<Integer> status = new ArrayList<>();

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }
}
