package com.pccg.pccguser.interfaces.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiResponse<T> extends Response {

    private long total;
    private Collection<T> data;

    public MultiResponse() {
    }

    public static <T> MultiResponse<T> of(Collection<T> data, long total) {
        MultiResponse<T> multiResponse = new MultiResponse();
        multiResponse.setSuccess(true);
        multiResponse.setData(data);
        multiResponse.setTotal(total);
        return multiResponse;
    }

    public static <T> MultiResponse<T> ofWithoutTotal(Collection<T> data) {
        return of(data, 0);
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return null == this.data ? new ArrayList() : new ArrayList(this.data);
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public static MultiResponse buildFailure(int errCode, String errMessage) {
        MultiResponse response = new MultiResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static MultiResponse buildSuccess() {
        MultiResponse response = new MultiResponse();
        response.setSuccess(true);
        return response;
    }
}
