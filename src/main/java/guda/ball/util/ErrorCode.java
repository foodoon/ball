package guda.ball.util;

import guda.mvc.spring.SpringBeanFactoryTool;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

/**
 * Created by foodoon on 2014/7/30.
 */
public class ErrorCode {

    public static String getMessage(String code){
        ReloadableResourceBundleMessageSource messageSource = (ReloadableResourceBundleMessageSource) SpringBeanFactoryTool.getBeanFactory().getBean("messageSource");
        String msg = messageSource.getMessage(code,null, Locale.getDefault());
        if(msg == null){
            msg = code;
        }
        return msg;
    }

    public static String getMessage(String code,Object[] args){
        ReloadableResourceBundleMessageSource messageSource = (ReloadableResourceBundleMessageSource)SpringBeanFactoryTool.getBeanFactory().getBean("messageSource");
        String msg = messageSource.getMessage(code,args, Locale.getDefault());
        if(msg == null){
            msg = code;
        }
        return msg;
    }
}
