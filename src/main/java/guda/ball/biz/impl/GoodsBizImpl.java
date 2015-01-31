package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.GoodsBiz;
import guda.ball.dao.GoodsDOMapper;
import guda.ball.dao.domain.GoodsDO;
import guda.ball.dao.domain.GoodsDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class GoodsBizImpl implements GoodsBiz{

    private final static Logger logger = LoggerFactory.getLogger(GoodsBizImpl.class);

    @Autowired
    private GoodsDOMapper goodsDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            GoodsDO goodsDO = goodsDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("goodsDO", goodsDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Goods error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            GoodsDOCriteria goodsDOCriteria = new GoodsDOCriteria();
            goodsDOCriteria.setStartRow(baseQuery.getStartRow());
            goodsDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = goodsDOMapper.countByExample(goodsDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<GoodsDO> goodsDOList = goodsDOMapper.selectByExample(goodsDOCriteria);
            bizResult.data.put("goodsDOList", goodsDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Goods list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            goodsDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete goods error", e);
        }
        return bizResult;
    }

    public BizResult create(GoodsDO goodsDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = goodsDOMapper.insert(goodsDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Goods error", e);
        }
        return bizResult;
    }

    public BizResult update(GoodsDO goodsDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = goodsDOMapper.updateByPrimaryKeySelective(goodsDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Goods error", e);
        }
        return bizResult;
    }

    }
