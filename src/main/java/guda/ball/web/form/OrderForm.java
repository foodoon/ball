package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.OrderDO;
import javax.validation.constraints.NotNull;

public class OrderForm {
                    @NotNull     private Integer status;

                    @NotNull     private Long sellerId;

                    @NotNull     private Long buyerId;

                    @NotNull     private Date deliveryTime;

                    @NotEmpty(message = "{不能为空}")
            private String leaveMsg;

                    @NotNull     private Long goodsId;

    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }
    public Long getSellerId() {
       return sellerId;
    }

    public void setSellerId(Long sellerId) {
       this.sellerId = sellerId;
    }
    public Long getBuyerId() {
       return buyerId;
    }

    public void setBuyerId(Long buyerId) {
       this.buyerId = buyerId;
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
       this.leaveMsg = leaveMsg;
    }
    public Long getGoodsId() {
       return goodsId;
    }

    public void setGoodsId(Long goodsId) {
       this.goodsId = goodsId;
    }

    public OrderDO toDO(){
       OrderDO orderDO  = new OrderDO();
            orderDO.setStatus(this.status);
                orderDO.setSellerId(this.sellerId);
                orderDO.setBuyerId(this.buyerId);
                orderDO.setDeliveryTime(this.deliveryTime);
                orderDO.setLeaveMsg(this.leaveMsg);
                orderDO.setGoodsId(this.goodsId);
           return orderDO;
}

}
