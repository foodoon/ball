package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.CourtSiteSectionBiz;
import guda.ball.dao.CourtSiteSectionDOMapper;
import guda.ball.dao.domain.CourtSiteSectionDO;
import guda.ball.dao.domain.CourtSiteSectionDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class CourtSiteSectionBizImpl implements CourtSiteSectionBiz{

    private final static Logger logger = LoggerFactory.getLogger(CourtSiteSectionBizImpl.class);

    @Autowired
    private CourtSiteSectionDOMapper courtSiteSectionDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            CourtSiteSectionDO courtSiteSectionDO = courtSiteSectionDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("courtSiteSectionDO", courtSiteSectionDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query CourtSiteSection error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            CourtSiteSectionDOCriteria courtSiteSectionDOCriteria = new CourtSiteSectionDOCriteria();
            courtSiteSectionDOCriteria.setStartRow(baseQuery.getStartRow());
            courtSiteSectionDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = courtSiteSectionDOMapper.countByExample(courtSiteSectionDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<CourtSiteSectionDO> courtSiteSectionDOList = courtSiteSectionDOMapper.selectByExample(courtSiteSectionDOCriteria);
            bizResult.data.put("courtSiteSectionDOList", courtSiteSectionDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view CourtSiteSection list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            courtSiteSectionDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete courtSiteSection error", e);
        }
        return bizResult;
    }

    public BizResult create(CourtSiteSectionDO courtSiteSectionDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtSiteSectionDOMapper.insert(courtSiteSectionDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create CourtSiteSection error", e);
        }
        return bizResult;
    }

    public BizResult update(CourtSiteSectionDO courtSiteSectionDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtSiteSectionDOMapper.updateByPrimaryKeySelective(courtSiteSectionDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update CourtSiteSection error", e);
        }
        return bizResult;
    }

    }
