package guda.ball.biz.helper;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by well on 2014/11/25.
 */
public class CustomMultipartResolver extends org.springframework.web.multipart.commons.CommonsMultipartResolver {

    @Override
    public boolean isMultipart(HttpServletRequest request) {

            return super.isMultipart(request);

    }
}
