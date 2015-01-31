package guda.ball.web.form;

import guda.ball.dao.domain.OrderDO;


public class OrderEditForm extends OrderForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    this.setStatus(orderDO.getStatus());
    this.setSellerId(orderDO.getSellerId());
    this.setBuyerId(orderDO.getBuyerId());
    this.setDeliveryTime(orderDO.getDeliveryTime());
    this.setLeaveMsg(orderDO.getLeaveMsg());
    this.setGoodsId(orderDO.getGoodsId());
}

}
