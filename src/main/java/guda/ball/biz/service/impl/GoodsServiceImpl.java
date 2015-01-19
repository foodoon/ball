package guda.ball.biz.service.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.service.GoodsService;
import guda.ball.dao.CourtDOMapper;
import guda.ball.dao.GoodsDOMapper;
import guda.ball.dao.OrderDOMapper;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.*;
import guda.ball.util.AppRequestMapping;
import guda.ball.util.AppRequestParam;
import guda.ball.util.BizResultHelper;
import guda.ball.util.CommonResultCode;
import guda.ball.util.enums.OrderStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by well on 2014/8/9.
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    private final static Logger log = LoggerFactory.getLogger(GoodsServiceImpl.class);
    @Autowired
    private GoodsDOMapper goodsDOMapper;
    @Autowired
    private CourtDOMapper courtDOMapper;
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;

    @AppRequestMapping(apiName = "goods.create", apiVersion = "1.0")
    public BizResult create(@AppRequestParam("sid") String sid, GoodsDO goodsDO) {
        if (!StringUtils.hasText(sid) || goodsDO == null || goodsDO.getCourtId()<1
                || !StringUtils.hasText(goodsDO.getGoodsName())
                || goodsDO.getPrice()<1) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }

        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        courtDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        if(CollectionUtils.isEmpty(courtDOs)){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        goodsDO.setGmtModify(new Date());
        goodsDO.setGmtCreate(new Date());
        try {
            goodsDOMapper.insert(goodsDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
             log.error("create error",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "goods.update", apiVersion = "1.0")
    public BizResult update(@AppRequestParam("sid") String sid, GoodsDO goodsDO) {
        if (!StringUtils.hasText(sid) || goodsDO == null || goodsDO.getId()<1
                || !StringUtils.hasText(goodsDO.getGoodsName())
                || goodsDO.getPrice()<1) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }

        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        courtDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        if(CollectionUtils.isEmpty(courtDOs)){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        GoodsDO goodsDO1 = goodsDOMapper.selectByPrimaryKey(goodsDO.getId());
        if(goodsDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.GOODS_NOT_EXIST);
        }
        goodsDO.setGmtModify(new Date());
        try {
            goodsDOMapper.updateByPrimaryKeySelective(goodsDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("create error",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "goods.delete", apiVersion = "1.0")
    public BizResult delete(@AppRequestParam("sid") String sid, @AppRequestParam("id") int id) {
        if (!StringUtils.hasText(sid) || id<1) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }

        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        courtDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        if(CollectionUtils.isEmpty(courtDOs)){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        GoodsDO goodsDO1 = goodsDOMapper.selectByPrimaryKey(id);
        if(goodsDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.GOODS_NOT_EXIST);
        }

        try {
            goodsDOMapper.deleteByPrimaryKey(id);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("delete error",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "goods.queryListByCourtId", apiVersion = "1.0")
    public BizResult queryListByCourtId(@AppRequestParam("sid") int courtId,@AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize") int pageSize) {
        if (courtId<1) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        GoodsDOCriteria goodsDOCriteria = new GoodsDOCriteria();
        goodsDOCriteria.createCriteria().andCourtIdEqualTo(courtId);
        goodsDOCriteria.setOrderByClause(" gmt_modify desc");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        List<GoodsDO> goodsDOs = goodsDOMapper.selectByExample(goodsDOCriteria);
        int count = goodsDOMapper.countByExample(goodsDOCriteria);
        baseQuery.setTotalCount(count);
        BizResult bizResult = new BizResult();
        bizResult.success = true;
        bizResult.data.put("query",baseQuery);
        bizResult.data.put("goodsList",goodsDOs);
        return bizResult;
    }
    @AppRequestMapping(apiName = "goods.queryOrderListByBuyer", apiVersion = "1.0")
    public BizResult queryOrderListByBuyer(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize") int pageSize) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
        orderDOCriteria.createCriteria().andBuyerIdEqualTo(userDO.getId());
        orderDOCriteria.setOrderByClause(" gmt_modify desc");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        List<OrderDO> orderDOs = orderDOMapper.selectByExample(orderDOCriteria);
        int count = orderDOMapper.countByExample(orderDOCriteria);
        baseQuery.setTotalCount(count);
        bizResult = new BizResult();
        bizResult.success = true;
        bizResult.data.put("query",baseQuery);
        bizResult.data.put("orderList",orderDOs);
        return bizResult;
    }
    @AppRequestMapping(apiName = "goods.queryOrderListBySeller", apiVersion = "1.0")
    public BizResult queryOrderListBySeller(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize") int pageSize) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        OrderDOCriteria orderDOCriteria = new OrderDOCriteria();
        orderDOCriteria.createCriteria().andSellerIdEqualTo(userDO.getId());
        orderDOCriteria.setOrderByClause(" gmt_modify desc");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        List<OrderDO> orderDOs = orderDOMapper.selectByExample(orderDOCriteria);
        int count = orderDOMapper.countByExample(orderDOCriteria);
        baseQuery.setTotalCount(count);
        bizResult = new BizResult();
        bizResult.success = true;
        bizResult.data.put("query",baseQuery);
        bizResult.data.put("orderList",orderDOs);
        return bizResult;
    }
    @AppRequestMapping(apiName = "goods.buy", apiVersion = "1.0")
    public BizResult buy(@AppRequestParam("sid") String sid,@AppRequestParam("goodsId")  int goodsId,@AppRequestParam("deliveryTime") Date deliveryTime,@AppRequestParam("leaveMsg") String leaveMsg) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        GoodsDO goodsDO = goodsDOMapper.selectByPrimaryKey(goodsId);
        if(goodsDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.GOODS_NOT_EXIST);
        }
        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(goodsDO.getCourtId());
        if(courtDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if(courtDO.getUserId() == userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        OrderDO orderDO = new OrderDO();
        orderDO.setBuyerId(userDO.getId());
        orderDO.setGmtCreate(new Date());
        orderDO.setGmtModify(new Date());
        orderDO.setGoodsId(goodsId);
        orderDO.setIsDeleted(0);
        orderDO.setStatus(OrderStatusEnum.INIT.value);
        orderDO.setDeliveryTime(deliveryTime);
        orderDO.setLeaveMsg(leaveMsg);
        orderDO.setSellerId(courtDO.getUserId());
        try {
            orderDOMapper.insert(orderDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("insert order error",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "goods.cancelBuy", apiVersion = "1.0")
    public BizResult cancelBuy(@AppRequestParam("sid") String sid,@AppRequestParam("orderId")  int orderId) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(orderId);
        if(orderDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_NOT_EXIST);
        }
        if(orderDO.getStatus()!= OrderStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_STATUS_ERROR);
        }
        if(orderDO.getBuyerId()!= userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        orderDO.setStatus(OrderStatusEnum.CANCEL.value);
        try {
            orderDOMapper.updateByPrimaryKeySelective(orderDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("e",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "goods.confirmBuy", apiVersion = "1.0")
    public BizResult confirmBuy(@AppRequestParam("sid") String sid, @AppRequestParam("orderId") int orderId) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(orderId);
        if(orderDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_NOT_EXIST);
        }
        if(orderDO.getStatus()!= OrderStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_STATUS_ERROR);
        }
        if(orderDO.getSellerId()!= userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        orderDO.setStatus(OrderStatusEnum.CONFIRM.value);
        try {
            orderDOMapper.updateByPrimaryKeySelective(orderDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("e",e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "goods.confirmGoods", apiVersion = "1.0")
    public BizResult confirmGoods(@AppRequestParam("sid") String sid, @AppRequestParam("orderId") int orderId) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(orderId);
        if(orderDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_NOT_EXIST);
        }
        if(orderDO.getStatus()!= OrderStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.ORDER_STATUS_ERROR);
        }
        if(orderDO.getBuyerId()!= userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        orderDO.setStatus(OrderStatusEnum.SUCCESS.value);
        try {
            orderDOMapper.updateByPrimaryKeySelective(orderDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("e",e);
        }
        return BizResultHelper.newCommonError();
    }
}
