package guda.ball.web.action.json;


import guda.ball.util.CommonResultCode;
import guda.ball.util.ServletRequestUtil;
import guda.ball.web.common.*;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by foodoon on 2014/7/29.
 */
@Controller
public class AppAction extends BaseJsonController{

    private final static Logger logger = LoggerFactory.getLogger(AppAction.class);

    @Autowired
    private AppServiceFactory appServiceFactory;

    @RequestMapping(value = "app.htm", method = RequestMethod.GET)
    public void execute(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
        //TODO 校验签名
        //TODO 校验登录
        //TODO
       // String method=request.getParameter("method");
        String apiName=request.getParameter("apiName");
        String apiVersion=request.getParameter("apiVersion");
        if(!StringUtils.hasText(apiName)||!StringUtils.hasText(apiVersion)){
            this.ajaxReturn(new AppResponse("缺少参数","无法找到处理器"),response);
            return ;
        }
        Map parameterMap = ServletRequestUtil.getAllParamter(request);
        AppRequest appRequest = new AppRequest();
        AppRequestKey appRequestKey = new AppRequestKey();
        appRequestKey.setApiName(apiName);
        appRequestKey.setApiVersion(apiVersion);

        AppHandle service = appServiceFactory.getService(appRequestKey);
        if(service == null){
            logger.error("cannot find handle for:" +appRequestKey);
            this.ajaxReturn(new AppResponse("系统错误","无法找到处理器"),response);
            return ;
        }
        appRequest.setAppHandle(service);

        appRequest.setAppRequestKey(appRequestKey);
        appRequest.setRequestParams(parameterMap);
        AppMethodInvoker appMethodInvoker = new AppMethodInvoker();
        AppResponse invoke = null;
        try {
            invoke = appMethodInvoker.invoke(appRequest, service);
            if(invoke.isSuccess()){
                if(invoke.getResult()!=null && invoke.getResult() instanceof BizResult){
                    BizResult bizResult = (BizResult)invoke.getResult();
                    if(!bizResult.success){
                        invoke.setSuccess(false);
                    }
                }
            }
            this.ajaxReturn(invoke,response);
            return;
        } catch (Exception e) {
            logger.error("invoke method error.",e);

        }
        invoke.setErrorCode(CommonResultCode.METHOD_INVOKE_ERROR);
        this.ajaxReturn(invoke,response);
    }

    public void setAppServiceFactory(AppServiceFactory appServiceFactory) {
        this.appServiceFactory = appServiceFactory;
    }
}
