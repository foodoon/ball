package guda.ball.biz.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.UserBiz;
import guda.ball.dao.SessionDOMapper;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.SessionDO;
import guda.ball.dao.domain.SessionDOCriteria;
import guda.ball.dao.domain.UserDO;
import guda.ball.dao.domain.UserDOCriteria;
import guda.ball.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class UserBizImpl implements UserBiz{

    private final static Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private SessionDOMapper sessionDOMapper;

    public BizResult detail(int id) {
        BizResult bizResult = new BizResult();
        try{
            UserDO userDO = userDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("userDO", userDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query User error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            UserDOCriteria userDOCriteria = new UserDOCriteria();
            userDOCriteria.setStartRow(baseQuery.getStartRow());
            userDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = userDOMapper.countByExample(userDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<UserDO> userDOList = userDOMapper.selectByExample(userDOCriteria);
            bizResult.data.put("userDOList", userDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view User list error", e);
        }
            return bizResult;
     }

    public BizResult delete(int id) {
        BizResult bizResult = new BizResult();
        try {
            userDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete user error", e);
        }
        return bizResult;
    }


    public BizResult create(UserDO userDO) {
        userDO.setGmtModify(new Date());
        userDO.setGmtCreate(new Date());
        BizResult bizResult = new BizResult();
        try {
            userDO.setGmtCreate(new Date());
            userDO.setGmtModify(new Date());
            int count = userDOMapper.insert(userDO);
            bizResult.data.put("count", userDO.getId());
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create User error", e);
            bizResult.msg =ErrorCode.getMessage(CommonResultCode.DATABASE_ERRROR);
            bizResult.code=CommonResultCode.DATABASE_ERRROR;
        }
        return bizResult;
    }

    public BizResult update(UserDO userDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = userDOMapper.updateByPrimaryKeySelective(userDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update User error", e);
        }
        return bizResult;
    }

    public BizResult login(String userName, String password) {
        BizResult bizResult = new BizResult();
        UserDOCriteria userDOCriteria = new UserDOCriteria();
        userDOCriteria.setStartRow(0);
        userDOCriteria.setPageSize(2);
        UserDOCriteria.Criteria criteria = userDOCriteria.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<UserDO> userDOs = userDOMapper.selectByExample(userDOCriteria);
        if(userDOs.size() == 0){
            BizResultHelper.setResultCode(bizResult,CommonResultCode.USER_NOT_EXIST);
            return bizResult;
        }else if(userDOs.size()>1){
            BizResultHelper.setResultCode(bizResult,CommonResultCode.USER_NAME_DUPLICATE);
            return bizResult;
        }

        //校验密码
        UserDO userDO = userDOs.get(0);
        if(!userDO.getPassword().equals(Md5.encode(password))){
            BizResultHelper.setResultCode(bizResult,CommonResultCode.USER_OR_PASSWORD_NOT_MATCH);
            return bizResult;
        }
        SessionDOCriteria sessionDOCriteria = new SessionDOCriteria();
        SessionDOCriteria.Criteria criteria1 = sessionDOCriteria.createCriteria();
        criteria1.andUserIdEqualTo(userDO.getId());
        sessionDOCriteria.setStartRow(0);
        sessionDOCriteria.setPageSize(2);
        List<SessionDO> sessionDOs = sessionDOMapper.selectByExample(sessionDOCriteria);
        String sid = UUID.randomUUID().toString();
        if(sessionDOs.size() == 0){
            SessionDO sessionDO = new SessionDO();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            sessionDO.setExpireTime(calendar.getTime());
            sessionDO.setGmtCreate(new Date());
            sessionDO.setGmtModify(new Date());
            sessionDO.setUserId(userDO.getId());
            sessionDO.setsId(sid);
            sessionDOMapper.insert(sessionDO);
            bizResult.data.put("sid",sid);
            bizResult.success = true;
            return bizResult;
        }else if(sessionDOs.size()==1){
            SessionDO sessionDO = sessionDOs.get(0);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            sessionDO.setExpireTime(calendar.getTime());
            sessionDO.setGmtModify(new Date());
            sessionDO.setsId(sid);
            sessionDOMapper.updateByPrimaryKeySelective(sessionDO);
            bizResult.data.put("sid",sid);
            bizResult.success = true;
            return bizResult;
        }else{
            throw new RuntimeException("session more than 1 rows.username:"+userName);
        }
    }

    public BizResult loginOut(String sid) {
        return null;
    }


}
