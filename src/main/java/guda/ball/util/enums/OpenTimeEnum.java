package guda.ball.util.enums;

/**
 * Created by foodoon on 2014/8/5.
 */
public enum OpenTimeEnum {

    ALL("ALL","所有时间均开放"),
    WEEKEND("WEEKEND","仅周末开放"),
    WORKDAY("WORKDAY","仅工作日开放");

    public String value;

    public String msg;

    private OpenTimeEnum(String value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static OpenTimeEnum getByValue(String value){
        OpenTimeEnum[] values = OpenTimeEnum.values();
        for(OpenTimeEnum openTimeEnum:values){
            if(openTimeEnum.value.equals(value)){
                return openTimeEnum;
            }
        }
        return null;
    }
}
