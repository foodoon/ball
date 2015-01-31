package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.CourtApplyBiz;
import guda.ball.dao.CourtApplyDOMapper;
import guda.ball.dao.domain.CourtApplyDO;
import guda.ball.dao.domain.CourtApplyDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class CourtApplyBizImpl implements CourtApplyBiz{

    private final static Logger logger = LoggerFactory.getLogger(CourtApplyBizImpl.class);

    @Autowired
    private CourtApplyDOMapper courtApplyDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("courtApplyDO", courtApplyDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query CourtApply error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
            courtApplyDOCriteria.setStartRow(baseQuery.getStartRow());
            courtApplyDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<CourtApplyDO> courtApplyDOList = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
            bizResult.data.put("courtApplyDOList", courtApplyDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view CourtApply list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            courtApplyDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete courtApply error", e);
        }
        return bizResult;
    }

    public BizResult create(CourtApplyDO courtApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            long count = courtApplyDOMapper.insert(courtApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create CourtApply error", e);
        }
        return bizResult;
    }

    public BizResult update(CourtApplyDO courtApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtApplyDOMapper.updateByPrimaryKeySelective(courtApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update CourtApply error", e);
        }
        return bizResult;
    }

    }
