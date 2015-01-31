package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.ChallengeMemberBiz;
import guda.ball.dao.ChallengeMemberDOMapper;
import guda.ball.dao.domain.ChallengeMemberDO;
import guda.ball.dao.domain.ChallengeMemberDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class ChallengeMemberBizImpl implements ChallengeMemberBiz{

    private final static Logger logger = LoggerFactory.getLogger(ChallengeMemberBizImpl.class);

    @Autowired
    private ChallengeMemberDOMapper challengeMemberDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            ChallengeMemberDO challengeMemberDO = challengeMemberDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("challengeMemberDO", challengeMemberDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query ChallengeMember error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            ChallengeMemberDOCriteria challengeMemberDOCriteria = new ChallengeMemberDOCriteria();
            challengeMemberDOCriteria.setStartRow(baseQuery.getStartRow());
            challengeMemberDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = challengeMemberDOMapper.countByExample(challengeMemberDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<ChallengeMemberDO> challengeMemberDOList = challengeMemberDOMapper.selectByExample(challengeMemberDOCriteria);
            bizResult.data.put("challengeMemberDOList", challengeMemberDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view ChallengeMember list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            challengeMemberDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete challengeMember error", e);
        }
        return bizResult;
    }

    public BizResult create(ChallengeMemberDO challengeMemberDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = challengeMemberDOMapper.insert(challengeMemberDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create ChallengeMember error", e);
        }
        return bizResult;
    }

    public BizResult update(ChallengeMemberDO challengeMemberDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = challengeMemberDOMapper.updateByPrimaryKeySelective(challengeMemberDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update ChallengeMember error", e);
        }
        return bizResult;
    }

    }
