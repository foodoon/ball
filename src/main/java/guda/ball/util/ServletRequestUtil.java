package guda.ball.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by foodoon on 2014/7/30.
 */
public class ServletRequestUtil {

    public static Map<String,String> getAllParamter(HttpServletRequest httpServletRequest){
        if(httpServletRequest == null){
            return Collections.emptyMap();
        }
        Map<String,String> p = new HashMap<String,String>();
        Enumeration parameterNames = httpServletRequest.getParameterNames();
        while(parameterNames.hasMoreElements()){
            Object o = parameterNames.nextElement();
            p.put(o.toString(),httpServletRequest.getParameter(o.toString()));
        }
        return p;
    }
}
