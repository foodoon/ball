package guda.ball.web.common;


import guda.ball.util.AppRequestParam;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.text.ParseException;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppMethodInvoker {

    public AppResponse invoke(AppRequest appRequest, AppHandle appHandle) throws IllegalAccessException, ParseException, InstantiationException {
        Object[] args = new Object[0];
        AppResponse appResponse = new AppResponse();

        args = resolveArguments(appHandle, appRequest);
        Object res = null;
        if (args == null) {
            res = ReflectionUtils.invokeMethod(appHandle.getHandleMethod(), appRequest.getAppHandle().getHandleObject());

        } else {
            res = ReflectionUtils.invokeMethod(appHandle.getHandleMethod(), appRequest.getAppHandle().getHandleObject(), args);
        }
        appResponse.setResult(res);
        appResponse.setSuccess(true);
        return appResponse;
    }

    private Object[] resolveArguments(AppHandle appHandle, AppRequest appRequest) throws IllegalAccessException, ParseException, InstantiationException {
        Class[] paramTypes = appHandle.getHandleMethod().getParameterTypes();
        if (paramTypes.length == 0) {
            return null;
        }
        Annotation[][] parameterAnnotations = appHandle.getHandleMethod().getParameterAnnotations();
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < args.length; i++) {
            Class param = paramTypes[i];
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            if(parameterAnnotation!=null && parameterAnnotation.length>0 && parameterAnnotation[0] instanceof  AppRequestParam) {
                String val = appRequest.getRequestParams().get(((AppRequestParam)parameterAnnotation[0]).value());
                args[i] = ReflectTool.resolveField(param, appRequest.getRequestParams(), val);
            }else{
                args[i] = ReflectTool.resolveField(param, appRequest.getRequestParams(), null);
            }
        }
        return args;
    }



}
