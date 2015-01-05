package guda.ball.web.form;

import guda.ball.dao.domain.OrderDO;


public class OrderEditForm extends OrderForm{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDO toDO(){
        OrderDO orderDO  =super.toDO();
        orderDO.setId(this.id);
        return orderDO;
    }

    public void initForm(OrderDO orderDO){
        if(orderDO == null){
        return ;
    }
    this.setGoodsId(orderDO.getGoodsId());
    this.setLeaveMsg(orderDO.getLeaveMsg());
    this.setDeliveryTime(orderDO.getDeliveryTime());
    this.setBuyerId(orderDO.getBuyerId());
    this.setSellerId(orderDO.getSellerId());
    this.setStatus(orderDO.getStatus());
}

}
