package guda.ball.biz.entity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by foodoon on 2014/7/31.
 */
public class ApiFactory implements InitializingBean{

    private List<ApiDefine> apiDefineList = new ArrayList<ApiDefine>();

    private Map<ApiKey, ApiDefine> apiMap = new HashMap<ApiKey, ApiDefine>();

    public List<ApiDefine> getApiDefineList() {
        return apiDefineList;
    }

    public void setApiDefineList(List<ApiDefine> apiDefineList) {
        this.apiDefineList = apiDefineList;
    }

    public Map<ApiKey, ApiDefine> getApiMap() {
        return apiMap;
    }

    public void setApiMap(Map<ApiKey, ApiDefine> apiMap) {
        this.apiMap = apiMap;
    }

    public void afterPropertiesSet() throws Exception {
        for(ApiDefine apiDefine :apiDefineList){
            ApiKey apiKey = new ApiKey();
            apiKey.setApiName(apiDefine.getApiName());
            apiKey.setApiVersion(apiDefine.getApiVersion());
            if(StringUtils.hasText(apiKey.getApiVersion())&&StringUtils.hasText(apiKey.getApiName())) {
                apiMap.put(apiKey, apiDefine);
            }
        }
    }
}
