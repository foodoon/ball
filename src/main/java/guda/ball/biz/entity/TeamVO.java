package guda.ball.biz.entity;

import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.UserDO;
import guda.ball.util.DateHelper;
import guda.ball.util.enums.BooleanEnum;

/**
 * Created by foodoon on 2015/1/21.
 */
public class TeamVO extends TeamDO {

    private String canJoinCN;

    private int memberCount;

    private String createDate;

    private int applyBallCount;
    private int ballCount;

    private UserDO teamLeader;


    public String getCanJoinCN() {
        return canJoinCN;
    }

    public void setCanJoinCN(String canJoinCN) {
        this.canJoinCN = canJoinCN;
    }

    public TeamVO(){

    }

    public TeamVO(TeamDO teamDO){
        this.setCanJoin(teamDO.getCanJoin());
        this.setId(teamDO.getId());
        this.setName(teamDO.getName());
        this.setTeamDesc(teamDO.getTeamDesc());
        this.setTeamType(teamDO.getTeamType());
        this.setUserId(teamDO.getUserId());
        this.setCanJoinCN(BooleanEnum.getByValue(teamDO.getCanJoin()).msg);
        this.setArea(teamDO.getArea());
        this.setHomeCourt(teamDO.getHomeCourt());
        this.setJerseyColor(teamDO.getJerseyColor());
        this.setCreateDate(DateHelper.formatShowCN(teamDO.getGmtCreate()));
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getApplyBallCount() {
        return applyBallCount;
    }

    public void setApplyBallCount(int applyBallCount) {
        this.applyBallCount = applyBallCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public UserDO getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(UserDO teamLeader) {
        this.teamLeader = teamLeader;
    }
}
