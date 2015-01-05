package guda.ball.web.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.lang.reflect.Method;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppHandle {

    private Object handleObject;

    private Method handleMethod;

    private String beanName;

    public Object getHandleObject() {
        return handleObject;
    }

    public void setHandleObject(Object handleObject) {
        this.handleObject = handleObject;
    }

    public Method getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(Method handleMethod) {
        this.handleMethod = handleMethod;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String toString(){
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
