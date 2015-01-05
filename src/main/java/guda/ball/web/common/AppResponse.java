package guda.ball.web.common;

/**
 * Created by foodoon on 2014/7/29.
 */
public class AppResponse {

    private Object result;

    private boolean isSuccess;

    private String errorCode;

    private String errorMsg;

    public AppResponse(){

    }

    public AppResponse(String errorCode,String errorMsg){
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
