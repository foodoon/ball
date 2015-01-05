package guda.ball.web.common;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppServiceFactory {

    Map<AppRequestKey,AppHandle> appHandler = new HashMap<AppRequestKey,AppHandle>();

    public void registerService(AppRequestKey appRequestKey,AppHandle appHandle){
        if(appRequestKey == null|| !StringUtils.hasText(appRequestKey.getApiName()) ||!StringUtils.hasText(appRequestKey.getApiVersion())){
            throw new RuntimeException("register app service error,appRequestKey error" + appRequestKey);
        }
        appHandler.put(appRequestKey,appHandle);
    }

    public AppHandle getService(AppRequestKey appRequestKey){
        if(appRequestKey == null|| !StringUtils.hasText(appRequestKey.getApiName()) ||!StringUtils.hasText(appRequestKey.getApiVersion())){
            return null;
        }
        return appHandler.get(appRequestKey);
    }

    public AppHandle getService(String apiVersion ,String apiName){
        if(!StringUtils.hasText(apiVersion) ||!StringUtils.hasText(apiName)){
            return null;
        }
        AppRequestKey appRequestKey = new AppRequestKey();
        appRequestKey.setApiName(apiName);
        appRequestKey.setApiVersion(apiVersion);
        return appHandler.get(appRequestKey);
    }
}
