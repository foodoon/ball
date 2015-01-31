package guda.ball.biz.impl;

import java.util.List;

import guda.ball.biz.query.CourtSiteQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.CourtSiteBiz;
import guda.ball.dao.CourtSiteDOMapper;
import guda.ball.dao.domain.CourtSiteDO;
import guda.ball.dao.domain.CourtSiteDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class CourtSiteBizImpl implements CourtSiteBiz{

    private final static Logger logger = LoggerFactory.getLogger(CourtSiteBizImpl.class);

    @Autowired
    private CourtSiteDOMapper courtSiteDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            CourtSiteDO courtSiteDO = courtSiteDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("courtSiteDO", courtSiteDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query CourtSite error",e);
        }
        return bizResult;
}

    public BizResult list(CourtSiteQuery courtSiteQuery) {
        BizResult bizResult = new BizResult();
        try {
            CourtSiteDOCriteria courtSiteDOCriteria = new CourtSiteDOCriteria();
            courtSiteDOCriteria.setStartRow(courtSiteQuery.getStartRow());
            courtSiteDOCriteria.setPageSize(courtSiteQuery.getPageSize());
            courtSiteDOCriteria.createCriteria().andCourtIdEqualTo(courtSiteQuery.getCourtId());
            int totalCount = courtSiteDOMapper.countByExample(courtSiteDOCriteria);
            courtSiteQuery.setTotalCount(totalCount);
            List<CourtSiteDO> courtSiteDOList = courtSiteDOMapper.selectByExample(courtSiteDOCriteria);
            bizResult.data.put("courtSiteDOList", courtSiteDOList);
            bizResult.data.put("query", courtSiteQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view CourtSite list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            courtSiteDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete courtSite error", e);
        }
        return bizResult;
    }

    public BizResult create(CourtSiteDO courtSiteDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtSiteDOMapper.insert(courtSiteDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create CourtSite error", e);
        }
        return bizResult;
    }

    public BizResult update(CourtSiteDO courtSiteDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtSiteDOMapper.updateByPrimaryKeySelective(courtSiteDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update CourtSite error", e);
        }
        return bizResult;
    }

    }
