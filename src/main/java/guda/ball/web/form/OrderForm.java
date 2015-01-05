package guda.ball.web.form;

import guda.ball.dao.domain.OrderDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderForm {
                    @NotNull     private Integer goodsId;

                    @NotEmpty(message = "{不能为空}")
            private String leaveMsg;

                    @NotNull     private Date deliveryTime;

                    @NotNull     private Integer buyerId;

                    @NotNull     private Integer sellerId;

                    @NotNull     private Integer status;

    public Integer getGoodsId() {
       return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
       this.goodsId = goodsId;
    }
    public String getLeaveMsg() {
       return leaveMsg;
    }

    public void setLeaveMsg(String leaveMsg) {
       this.leaveMsg = leaveMsg;
    }
    public Date getDeliveryTime() {
       return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
       this.deliveryTime = deliveryTime;
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
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }

    public OrderDO toDO(){
       OrderDO orderDO  = new OrderDO();
            orderDO.setGoodsId(this.goodsId);
                orderDO.setLeaveMsg(this.leaveMsg);
                orderDO.setDeliveryTime(this.deliveryTime);
                orderDO.setBuyerId(this.buyerId);
                orderDO.setSellerId(this.sellerId);
                orderDO.setStatus(this.status);
           return orderDO;
}

}
