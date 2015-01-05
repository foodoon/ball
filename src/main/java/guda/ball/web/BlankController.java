/**
 * zoneland.net Inc.
 * Copyright (c) 2002-2012 All Rights Reserved.
 */
package guda.ball.web;

import guda.tools.web.util.RequestUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 
 * @author gag
 * @version $Id: BlankController.java, v 0.1 2012-4-26 9:16:33 gag Exp $
 */
@Controller
public class BlankController {

    @RequestMapping(value = "**/*.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        return RequestUtil.resolveVM(request);
    }


}
