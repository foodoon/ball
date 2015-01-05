package guda.ball.util.enums;

/**
 * Created by foodoon on 2014/8/3.
 */
public enum ApplyStatusEnum {

    INIT(0,"等待对方同意"),
    PASS(1,"同意"),
    REJECT(2,"拒绝");

    public int value;

    public String msg;

    private ApplyStatusEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static ApplyStatusEnum getByValue(int value){
        ApplyStatusEnum[] values = ApplyStatusEnum.values();
        for(ApplyStatusEnum applyStatusEnum:values){
            if(applyStatusEnum.value == value){
                return applyStatusEnum;
            }
        }
        return null;
    }


}
