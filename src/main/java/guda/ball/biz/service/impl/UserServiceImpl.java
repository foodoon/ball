package guda.ball.biz.service.impl;


import guda.ball.biz.SessionBiz;
import guda.ball.biz.UserBiz;
import guda.ball.biz.entity.AppUserForm;
import guda.ball.biz.entity.UserVO;
import guda.ball.biz.service.UserService;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.SessionDO;
import guda.ball.dao.domain.UserDO;
import guda.ball.dao.domain.UserDOCriteria;
import guda.ball.util.*;
import guda.tools.web.page.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by foodoon on 2014/7/31.
 */
@Service
public class UserServiceImpl implements UserService {
    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private SessionBiz sessionBiz;

    @AppRequestMapping(apiName = "user.reg", apiVersion = "1.0")
    public BizResult create(AppUserForm appUserForm) {
        if (!StringUtils.hasText(appUserForm.getPassword2()) || !StringUtils.hasText(appUserForm.getPassword()) || !StringUtils.hasText(appUserForm.getUserName())) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        appUserForm.setPassword(Md5.encode(appUserForm.getPassword()));
        //判断用户名是否重复
        UserDOCriteria userDOCriteria = new UserDOCriteria();
        UserDOCriteria.Criteria criteria = userDOCriteria.createCriteria();
        criteria.andUserNameEqualTo(appUserForm.getUserName());
        try {
            int i = userDOMapper.countByExample(userDOCriteria);
            if (i > 0) {
                return BizResultHelper.newResultCode(CommonResultCode.USER_NAME_DUPLICATE);
            }
            return userBiz.create(appUserForm);
        } catch (Exception e) {
            log.error("create user error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "user.update", apiVersion = "1.0")
    public BizResult update(@AppRequestParam("sid") String sid, UserDO userDO) {
        if(sid == null){
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        SessionDO sessionDO = sessionBiz.querySessionBySID(sid);
        if (sessionDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        if ((new Date()).after(sessionDO.getExpireTime())) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        userDO.setId(sessionDO.getUserId());
        if(StringUtils.hasText(userDO.getPassword())){
            userDO.setPassword(Md5.encode(userDO.getPassword()));
        }
        try {
            int i = userDOMapper.updateByPrimaryKeySelective(userDO);
            if (i == 1) {
                return BizResultHelper.newSuccess();
            }
        } catch (Exception e) {
            log.error("update user error", e);
        }

        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "user.queryUserInfo", apiVersion = "1.0")
    public BizResult queryUserInfo(@AppRequestParam("sid") String sid) {
        if(sid == null){
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        SessionDO sessionDO = sessionBiz.querySessionBySID(sid);
        if (sessionDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        if ((new Date()).after(sessionDO.getExpireTime())) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }

        try {
            UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
            BizResult bizResult = new BizResult();
            bizResult.data.put("user",new UserVO(userDO));
            bizResult.success = true;
            return bizResult;
        } catch (Exception e) {
            log.error("query user error", e);
        }

        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "user.queryUserInfoById", apiVersion = "1.0")
    public BizResult queryUserInfoById(@AppRequestParam("sid") String sid, @AppRequestParam("userId") int userId) {
        if(sid == null){
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        SessionDO sessionDO = sessionBiz.querySessionBySID(sid);
        if (sessionDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        if ((new Date()).after(sessionDO.getExpireTime())) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }

        try {
            UserDO userDO = userDOMapper.selectByPrimaryKey(userId);
            BizResult bizResult = new BizResult();
            bizResult.data.put("user",new UserVO(userDO));
            bizResult.success = true;
            return bizResult;
        } catch (Exception e) {
            log.error("query user error", e);
        }

        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "user.login", apiVersion = "1.0")
    public BizResult login(@AppRequestParam("userName") String userName,@AppRequestParam("password")  String password) {
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        try {
            return userBiz.login(userName, password);
        } catch (Exception e) {
            log.error("login error", e);
            return BizResultHelper.newCommonError();
        }
    }

    @AppRequestMapping(apiName = "user.loginOut", apiVersion = "1.0")
    public BizResult loginOut(@AppRequestParam("sid") String sid) {
        if(sid == null){
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        SessionDO sessionDO = sessionBiz.querySessionBySID(sid);
        if (sessionDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        if ((new Date()).after(sessionDO.getExpireTime())) {
            return BizResultHelper.newResultCode(CommonResultCode.NEED_LOGIN);
        }
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR,1970);
        sessionDO.setExpireTime(instance.getTime());
        try {
            return sessionBiz.update(sessionDO);

        } catch (Exception e) {
            log.error("user login out error", e);
        }

        return BizResultHelper.newCommonError();
    }


}
