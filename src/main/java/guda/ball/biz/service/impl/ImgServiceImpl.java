package guda.ball.biz.service.impl;

import guda.ball.biz.service.ImgService;
import guda.ball.util.AppRequestMapping;
import guda.ball.web.action.api.Index;
import guda.tools.web.page.BizResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by well on 2015/1/7.
 */

public class ImgServiceImpl implements ImgService{


    private Map<String,String> imgMap = new HashMap<String,String>();

    @AppRequestMapping(apiName = "img.list", apiVersion = "1.0")
    public BizResult list() {
        BizResult bizResult = new BizResult();
        bizResult.success = true;

        bizResult.data.put("list",imgMap);
        return bizResult;
    }

    public void setImgMap(Map<String, String> imgMap) {
        this.imgMap = imgMap;
    }
}
