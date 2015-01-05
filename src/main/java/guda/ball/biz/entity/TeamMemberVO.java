package guda.ball.biz.entity;


/**
 * Created by foodoon on 2014/8/3.
 */
public class TeamMemberVO  {


    private int memberUserId;

    private String memberUserName;

    private String memberUserRealName;

    public int getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(int memberUserId) {
        this.memberUserId = memberUserId;
    }

    public String getMemberUserName() {
        return memberUserName;
    }

    public void setMemberUserName(String memberUserName) {
        this.memberUserName = memberUserName;
    }

    public String getMemberUserRealName() {
        return memberUserRealName;
    }

    public void setMemberUserRealName(String memberUserRealName) {
        this.memberUserRealName = memberUserRealName;
    }
}
