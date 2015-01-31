package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class OrderDO {
    private Long id;

    @GenField(cn="商品ID",order=1,inSearchForm = false,canNull = false)
    private Long goodsId;
    @GenField(cn="留言",order=1,inSearchForm = false,canNull = false)
    private String leaveMsg;
    @GenField(cn="配送时间",order=1,inSearchForm = false,canNull = false)
    private Date deliveryTime;
    @GenField(cn="买家ID",order=1,inSearchForm = false,canNull = false)
    private Long buyerId;
    @GenField(cn="卖家ID",order=1,inSearchForm = false,canNull = false)
    private Long sellerId;
    @GenField(cn="订单状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;


    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
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