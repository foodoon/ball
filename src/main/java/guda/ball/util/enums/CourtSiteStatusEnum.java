package guda.ball.util.enums;

/**
 * Created by foodoon on 2015/1/31.
 */
public enum CourtSiteStatusEnum {

    INIT(0,"可预定"),
    BOOK(1,"已预定"),
    ;

    public int value;

    public String msg;

    private CourtSiteStatusEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }
    public static CourtSiteStatusEnum getByValue(int value){
        CourtSiteStatusEnum[] values = CourtSiteStatusEnum.values();
        for(CourtSiteStatusEnum courtSiteStatusEnum:values){
            if(courtSiteStatusEnum.value == value){
                return courtSiteStatusEnum;
            }
        }
        return null;
    }

}
