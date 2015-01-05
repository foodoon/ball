package guda.ball.util.enums;

/**
 * Created by well on 2014/8/10.
 */
public enum CommentTypeEnum {

    CHALLENGE("challenge","比赛"),
    TEAM("team","球队"),
    COURT("court","场地"),
    USER("user","球员");

    public String value;

    public String msg;

    private CommentTypeEnum(String value,String msg){
        this.value = value;
        this.msg = msg;
    }
    public static CommentTypeEnum getByValue(String value){
        CommentTypeEnum[] values = CommentTypeEnum.values();
        for(CommentTypeEnum commentTypeEnum:values){
            if(commentTypeEnum.value.equals(value)){
                return commentTypeEnum;
            }
        }
        return null;
    }
}
