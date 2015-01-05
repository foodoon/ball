package guda.ball.web.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppRequestKey {

    private String apiName;

    private String apiVersion;

    public String getApiName() {
        return apiName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppRequestKey)) return false;

        AppRequestKey that = (AppRequestKey) o;

        if (!apiName.equals(that.apiName)) return false;
        if (!apiVersion.equals(that.apiVersion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = apiName.hashCode();
        result = 31 * result + apiVersion.hashCode();
        return result;
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


    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
