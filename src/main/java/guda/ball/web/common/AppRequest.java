package guda.ball.web.common;

import java.util.Map;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppRequest {

    private AppRequestKey appRequestKey;

    private Map<String,String> requestParams;

    private Object[] resolvePrams;

    private AppHandle appHandle;

    public AppHandle getAppHandle() {
        return appHandle;
    }

    public void setAppHandle(AppHandle appHandle) {
        this.appHandle = appHandle;
    }

    public AppRequestKey getAppRequestKey() {
        return appRequestKey;
    }

    public void setAppRequestKey(AppRequestKey appRequestKey) {
        this.appRequestKey = appRequestKey;
    }

    public Map<String, String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, String> requestParams) {
        this.requestParams = requestParams;
    }

    public Object[] getResolvePrams() {
        return resolvePrams;
    }

    public void setResolvePrams(Object[] resolvePrams) {
        this.resolvePrams = resolvePrams;
    }
}
