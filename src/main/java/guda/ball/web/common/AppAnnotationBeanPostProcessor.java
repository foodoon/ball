package guda.ball.web.common;

import guda.ball.util.AppRequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(AppAnnotationBeanPostProcessor.class);

    private AppServiceFactory appServiceFactory;

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {
        ReflectionUtils.doWithMethods(bean.getClass(),new ReflectionUtils.MethodCallback(){

            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                AppRequestMapping annotation = method.getAnnotation(AppRequestMapping.class);
                if(annotation!=null){
                    String apiName = annotation.apiName();
                    String apiVersion = annotation.apiVersion();
                    if(!StringUtils.hasText(apiName)||!StringUtils.hasText(apiVersion)){
                        throw new RuntimeException("apiName or apiVersion cannot null.bean class:" + bean.getClass());
                    }
                    AppRequestKey appRequestKey = new AppRequestKey();
                    appRequestKey.setApiVersion(apiVersion);
                    appRequestKey.setApiName(apiName);
                    AppHandle appHandle = new AppHandle();
                    appHandle.setBeanName(beanName);
                    appHandle.setHandleMethod(method);
                    appHandle.setHandleObject(bean);
                    logger.info("register app appServiceFactory:" + appServiceFactory + ",handle:" + appHandle);
                    appServiceFactory.registerService(appRequestKey,appHandle);
                    logger.info("register app service:" + appRequestKey + ",handle:" + appHandle);
                }
            }
        });

        return true;
    }

    public void setAppServiceFactory(AppServiceFactory appServiceFactory) {
        this.appServiceFactory = appServiceFactory;
    }
}
