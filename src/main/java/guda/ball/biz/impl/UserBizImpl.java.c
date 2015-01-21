package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.UserBiz;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.UserDO;
import guda.ball.dao.domain.UserDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class UserBizImpl implements UserBiz{

    private final static Logger logger = LoggerFactory.getLogger(UserBizImpl.class);

    @Autowired
    private UserDOMapper userDOMapper;

    public BizResult detail(long id) {
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

    public BizResult delete(long id) {
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
        BizResult bizResult = new BizResult();
        try {
            int count = userDOMapper.insert(userDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create User error", e);
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

    }
