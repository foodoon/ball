package guda.ball.util.enums;

/**
 * Created by well on 2014/8/17.
 */
public enum CourtTypeEnum {
    TRHEE("3","3人"),
    FIVE("5","5人"),
    SEVEN("7","7人"),
    NINE("9","9人"),
    ELEVEN("11","11人"),
    ;

    public String value;

    public String msg;

    private CourtTypeEnum(String value,String msg){
        this.value = value;
        this.msg = msg;
    }

    public static CourtTypeEnum getByValue(String value){
        CourtTypeEnum[] values = CourtTypeEnum.values();
        for(CourtTypeEnum courtTypeEnum:values){
            if(courtTypeEnum.value.equals(value)){
                return courtTypeEnum;
            }
        }
        return null;
    }
}
