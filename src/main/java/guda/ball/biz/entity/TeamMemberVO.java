package guda.ball.biz.entity;


import guda.ball.dao.domain.UserDO;

/**
 * Created by foodoon on 2014/8/3.
 */
public class TeamMemberVO  {


    private long memberUserId;

    private String memberUserName;

    private String memberUserRealName;

    private String img;

    public TeamMemberVO(){

    }

    public TeamMemberVO(UserDO userDO1){
        this.setImg(userDO1.getImg());
        this.setMemberUserId(userDO1.getId());
        this.setMemberUserName(userDO1.getUserName());
        this.setMemberUserRealName(userDO1.getRealName());
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getMemberUserId() {
        return memberUserId;
    }

    public void setMemberUserId(long memberUserId) {
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
