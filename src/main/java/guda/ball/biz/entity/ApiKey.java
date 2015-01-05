package guda.ball.biz.entity;

/**
 * Created by foodoon on 2014/7/31.
 */
public class ApiKey {

    private String apiName;

    private String apiVersion;

    public ApiKey(){

    }

    public ApiKey(String apiName,String apiVersion){
        this.apiName = apiName;
        this.apiVersion = apiVersion;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiKey)) return false;

        ApiKey apiKey = (ApiKey) o;

        if (apiName != null ? !apiName.equals(apiKey.apiName) : apiKey.apiName != null) return false;
        if (apiVersion != null ? !apiVersion.equals(apiKey.apiVersion) : apiKey.apiVersion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = apiName != null ? apiName.hashCode() : 0;
        result = 31 * result + (apiVersion != null ? apiVersion.hashCode() : 0);
        return result;
    }
}
