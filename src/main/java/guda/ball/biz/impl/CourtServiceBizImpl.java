package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.CourtServiceBiz;
import guda.ball.dao.CourtServiceDOMapper;
import guda.ball.dao.domain.CourtServiceDO;
import guda.ball.dao.domain.CourtServiceDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class CourtServiceBizImpl implements CourtServiceBiz{

    private final static Logger logger = LoggerFactory.getLogger(CourtServiceBizImpl.class);

    @Autowired
    private CourtServiceDOMapper courtServiceDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            CourtServiceDO courtServiceDO = courtServiceDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("courtServiceDO", courtServiceDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query CourtService error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            CourtServiceDOCriteria courtServiceDOCriteria = new CourtServiceDOCriteria();
            courtServiceDOCriteria.setStartRow(baseQuery.getStartRow());
            courtServiceDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = courtServiceDOMapper.countByExample(courtServiceDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<CourtServiceDO> courtServiceDOList = courtServiceDOMapper.selectByExample(courtServiceDOCriteria);
            bizResult.data.put("courtServiceDOList", courtServiceDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view CourtService list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            courtServiceDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete courtService error", e);
        }
        return bizResult;
    }

    public BizResult create(CourtServiceDO courtServiceDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtServiceDOMapper.insert(courtServiceDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create CourtService error", e);
        }
        return bizResult;
    }

    public BizResult update(CourtServiceDO courtServiceDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = courtServiceDOMapper.updateByPrimaryKeySelective(courtServiceDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update CourtService error", e);
        }
        return bizResult;
    }

    }
