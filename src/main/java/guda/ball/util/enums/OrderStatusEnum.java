package guda.ball.util.enums;

/**
 * Created by well on 2014/8/9.
 */
public enum OrderStatusEnum {

    INIT(0,"等待卖家确认"),
    SUCCESS(1,"完成"),
    CANCEL(3,"买家已取消"),
    CONFIRM(2,"卖家已确认");


    public int value;

    public String msg;

    private OrderStatusEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static OrderStatusEnum getByValue(int value){
        OrderStatusEnum[] values = OrderStatusEnum.values();
        for(OrderStatusEnum orderStatusEnum:values){
            if(orderStatusEnum.value == value){
                return orderStatusEnum;
            }
        }
        return null;
    }
}
