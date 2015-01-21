package guda.ball.util.enums;

/**
 * Created by foodoon on 2014/8/3.
 */
public enum BooleanEnum {

    FALSE(0,"否"),
    TRUE(1,"是");

    public int value;

    public String msg;

    private BooleanEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static BooleanEnum getByValue(int value){
        BooleanEnum[] values = BooleanEnum.values();
        for(BooleanEnum booleanEnum:values){
            if(booleanEnum.value == (value)){
                return booleanEnum;
            }
        }
        return null;
    }
}
