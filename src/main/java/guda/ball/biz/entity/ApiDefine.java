package guda.ball.biz.entity;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by foodoon on 2014/7/31.
 */
public class ApiDefine {
    private String apiName;

    private String apiVersion;



    private Map<String,String> params = new TreeMap<String, String>();

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
