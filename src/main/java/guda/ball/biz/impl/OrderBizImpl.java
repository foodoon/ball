package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.OrderBiz;
import guda.ball.dao.OrderDOMapper;
import guda.ball.dao.domain.OrderDO;
import guda.ball.dao.domain.OrderDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class OrderBizImpl implements OrderBiz{

    private final static Logger logger = LoggerFactory.getLogger(OrderBizImpl.class);

    @Autowired
    private OrderDOMapper orderDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("orderDO", orderDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Order error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
            orderDOCriteria.setStartRow(baseQuery.getStartRow());
            orderDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = orderDOMapper.countByExample(orderDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<OrderDO> orderDOList = orderDOMapper.selectByExample(orderDOCriteria);
            bizResult.data.put("orderDOList", orderDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Order list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            orderDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete order error", e);
        }
        return bizResult;
    }

    public BizResult create(OrderDO orderDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = orderDOMapper.insert(orderDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Order error", e);
        }
        return bizResult;
    }

    public BizResult update(OrderDO orderDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = orderDOMapper.updateByPrimaryKeySelective(orderDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Order error", e);
        }
        return bizResult;
    }

    }
