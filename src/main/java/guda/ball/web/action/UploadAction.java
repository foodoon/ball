package guda.ball.web.action;


import guda.ball.biz.SessionBiz;
import guda.ball.biz.helper.FileHelper;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.SessionDO;
import guda.ball.dao.domain.UserDO;
import guda.ball.util.BizResultHelper;
import guda.ball.util.CommonResultCode;
import guda.ball.web.action.json.BaseJsonController;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by foodoon on 2014/11/22.
 */
@Controller
public class UploadAction extends BaseJsonController{

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileHelper fileHelper;
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping(value="user/upload.json",method= RequestMethod.POST)
    public void upload( MultipartFile file, String  sid,HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        try {
            BizResult bizResult = sessionBiz.checkSession(sid);
            if(!bizResult.success) {
                ajaxReturn(bizResult, response);
                return;
            }
            SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
            UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
            if(userDO == null){
                ajaxReturn(BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST), response);
                return;
            }


            String url = fileHelper.putFileToPlace(file);
            userDO.setImg(url);
            userDO.setGmtModify(new Date());
            userDOMapper.updateByPrimaryKey(userDO);
            bizResult = new BizResult();
            bizResult.success=true;
            bizResult.data.put("url",fileHelper.getFileSever()+url);
            ajaxReturn(bizResult, response);
            return;
        } catch (IOException e) {
            logger.error("",e);
        }
        ajaxReturn(BizResultHelper.newCommonError(), response);
    }

    private String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path;
        return basePath;
    }

}
