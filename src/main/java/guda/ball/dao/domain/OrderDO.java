package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class OrderDO {
    private Integer id;

    @GenField(cn="商品ID",order=1,inSearchForm = false,canNull = false)
    private Integer goodsId;

    @GenField(cn="留言",order=1,inSearchForm = false,canNull = false)
    private String leaveMsg;

    @GenField(cn="配送时间",order=1,inSearchForm = false,canNull = false)
    private Date deliveryTime;

    @GenField(cn="买家ID",order=1,inSearchForm = false,canNull = false)
    private Integer buyerId;
    @GenField(cn="卖家ID",order=1,inSearchForm = false,canNull = false)
    private Integer sellerId;
    @GenField(cn="订单状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getLeaveMsg() {
        return leaveMsg;
    }

    public void setLeaveMsg(String leaveMsg) {
        this.leaveMsg = leaveMsg == null ? null : leaveMsg.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}