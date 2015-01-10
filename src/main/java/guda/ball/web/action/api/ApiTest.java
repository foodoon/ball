package guda.ball.web.action.api;


import guda.ball.biz.UserBiz;
import guda.ball.util.CommonResultCode;
import guda.ball.util.ErrorCode;
import guda.ball.util.SignUtil;
import guda.ball.web.action.json.BaseJsonController;
import guda.tools.web.page.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by foodoon on 2014/7/31.
 */
@Controller
public class ApiTest extends BaseJsonController{

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "api/createUrl.htm", method = RequestMethod.GET)
    public void createUrl(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String requestUrl = request.getParameter("requestUrl");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if(!StringUtils.hasText(requestUrl)){
            modelMap.put("msg","requestUrl不能为空");
            this.ajaxReturn(modelMap,response);
            return;
        }


        String realUrl ="";
        if("user.login".equals(request.getParameter("apiName"))||"user.reg".equals(request.getParameter("apiName"))){
            realUrl = SignUtil.convert2Str(request.getParameterMap(), new String[]{"requestUrl"});
        }else{
            realUrl = SignUtil.convert2Str(request.getParameterMap(),new String[]{"requestUrl","userName","password"});
        }
        //如果Username  password不为空，处理登录
        if(StringUtils.hasText(userName)&&StringUtils.hasText(password)&&!"user.reg".equals(request.getParameter("apiName"))&&!"user.login".equals(request.getParameter("apiName"))) {
            BizResult login = userBiz.login(userName, password);

            if(!login.success){
                modelMap.put("msg", ErrorCode.getMessage(CommonResultCode.USER_OR_PASSWORD_NOT_MATCH));
                this.ajaxReturn(modelMap, response);
                return;
            }

            if(realUrl.length()>0){
                realUrl  = realUrl + "&sid=" + login.data.get("sid");
            }else{
                realUrl = "sid=" + login.data.get("sid");
            }
        }
        if(requestUrl.contains("&")&&requestUrl.contains("=")){
            modelMap.put("msg",requestUrl + "&" + realUrl);
        }else if(requestUrl.contains("?")&&requestUrl.contains("=")){
            modelMap.put("msg",requestUrl + "&" + realUrl);
        }else{
            modelMap.put("msg",requestUrl + "?" + realUrl);
        }

        this.ajaxReturn(modelMap,response);


    }
}
