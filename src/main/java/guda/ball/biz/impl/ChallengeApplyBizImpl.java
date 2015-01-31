package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.ChallengeApplyBiz;
import guda.ball.dao.ChallengeApplyDOMapper;
import guda.ball.dao.domain.ChallengeApplyDO;
import guda.ball.dao.domain.ChallengeApplyDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class ChallengeApplyBizImpl implements ChallengeApplyBiz{

    private final static Logger logger = LoggerFactory.getLogger(ChallengeApplyBizImpl.class);

    @Autowired
    private ChallengeApplyDOMapper challengeApplyDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("challengeApplyDO", challengeApplyDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query ChallengeApply error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.setStartRow(baseQuery.getStartRow());
            challengeApplyDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = challengeApplyDOMapper.countByExample(challengeApplyDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<ChallengeApplyDO> challengeApplyDOList = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            bizResult.data.put("challengeApplyDOList", challengeApplyDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view ChallengeApply list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            challengeApplyDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete challengeApply error", e);
        }
        return bizResult;
    }

    public BizResult create(ChallengeApplyDO challengeApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            long count = challengeApplyDOMapper.insert(challengeApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create ChallengeApply error", e);
        }
        return bizResult;
    }

    public BizResult update(ChallengeApplyDO challengeApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = challengeApplyDOMapper.updateByPrimaryKeySelective(challengeApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update ChallengeApply error", e);
        }
        return bizResult;
    }

    }
