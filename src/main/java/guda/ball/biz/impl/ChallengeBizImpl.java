package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.ChallengeBiz;
import guda.ball.dao.ChallengeDOMapper;
import guda.ball.dao.domain.ChallengeDO;
import guda.ball.dao.domain.ChallengeDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class ChallengeBizImpl implements ChallengeBiz{

    private final static Logger logger = LoggerFactory.getLogger(ChallengeBizImpl.class);

    @Autowired
    private ChallengeDOMapper challengeDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("challengeDO", challengeDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Challenge error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
            challengeDOCriteria.setStartRow(baseQuery.getStartRow());
            challengeDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = challengeDOMapper.countByExample(challengeDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<ChallengeDO> challengeDOList = challengeDOMapper.selectByExample(challengeDOCriteria);
            bizResult.data.put("challengeDOList", challengeDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Challenge list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            challengeDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete challenge error", e);
        }
        return bizResult;
    }

    public BizResult create(ChallengeDO challengeDO) {
        BizResult bizResult = new BizResult();
        try {
            long count = challengeDOMapper.insert(challengeDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Challenge error", e);
        }
        return bizResult;
    }

    public BizResult update(ChallengeDO challengeDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Challenge error", e);
        }
        return bizResult;
    }

    }
