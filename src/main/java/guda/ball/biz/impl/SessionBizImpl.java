package guda.ball.biz.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.dao.SessionDOMapper;
import guda.ball.dao.domain.SessionDO;
import guda.ball.dao.domain.SessionDOCriteria;
import guda.ball.util.BizResultHelper;
import guda.ball.util.CommonResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class SessionBizImpl implements SessionBiz{

    private final static Logger logger = LoggerFactory.getLogger(SessionBizImpl.class);

    @Autowired
    private SessionDOMapper sessionDOMapper;

    public BizResult detail(int id) {
        BizResult bizResult = new BizResult();
        try{
            SessionDO sessionDO = sessionDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("sessionDO", sessionDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Session error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            SessionDOCriteria sessionDOCriteria = new SessionDOCriteria();
            sessionDOCriteria.setStartRow(baseQuery.getStartRow());
            sessionDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = sessionDOMapper.countByExample(sessionDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<SessionDO> sessionDOList = sessionDOMapper.selectByExample(sessionDOCriteria);
            bizResult.data.put("sessionDOList", sessionDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Session list error", e);
        }
            return bizResult;
     }

    public BizResult delete(int id) {
        BizResult bizResult = new BizResult();
        try {
            sessionDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete session error", e);
        }
        return bizResult;
    }

    public BizResult create(SessionDO sessionDO) {
        BizResult bizResult = new BizResult();
        try {
            int id = sessionDOMapper.insert(sessionDO);
            bizResult.data.put("id", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Session error", e);
        }
        return bizResult;
    }

    public BizResult update(SessionDO sessionDO) {
        BizResult bizResult = new BizResult();
        try {
            int id = sessionDOMapper.updateByPrimaryKeySelective(sessionDO);
            bizResult.data.put("id", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Session error", e);
        }
        return bizResult;
    }

    public SessionDO querySessionBySID(String sid) {
        try {
            SessionDOCriteria sessionDOCriteria = new SessionDOCriteria();
            sessionDOCriteria.setStartRow(0);
            sessionDOCriteria.setPageSize(2);
            SessionDOCriteria.Criteria criteria = sessionDOCriteria.createCriteria();
            criteria.andSIdEqualTo(sid);
            List<SessionDO> sessionDOList = sessionDOMapper.selectByExample(sessionDOCriteria);
            if(sessionDOList.size()>1){
                throw new RuntimeException("数据库记录多于一条:"+sid);
            }else if(sessionDOList.size() == 1){
                return sessionDOList.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error("view Session list error", e);
        }
        return null;
    }

    public BizResult checkSession(String sid) {
        SessionDO sessionDO = querySessionBySID(sid);
        if(sessionDO == null || (new Date()).after(sessionDO.getExpireTime())){
            return BizResultHelper.newResultCode(CommonResultCode.SESSION_EXPIRE);
        }
        BizResult bizResult = new BizResult();
        bizResult.success = true;
        bizResult.data.put("sessionDO",sessionDO);
        return bizResult;
    }

}
