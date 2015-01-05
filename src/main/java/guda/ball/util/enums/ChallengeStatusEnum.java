package guda.ball.util.enums;

/**
 * Created by well on 2014/8/7.
 */
public enum ChallengeStatusEnum {

    FALSE(0,"未确认"),
    TRUE(1,"约战双方已确认");

    public int value;

    public String msg;

    private ChallengeStatusEnum(int value,String msg){
        this.value = value;
        this.msg = msg;
    }
    public static ChallengeStatusEnum getByValue(int value){
        ChallengeStatusEnum[] values = ChallengeStatusEnum.values();
        for(ChallengeStatusEnum challengeStatusEnum:values){
            if(challengeStatusEnum.value == value){
                return challengeStatusEnum;
            }
        }
        return null;
    }

}
