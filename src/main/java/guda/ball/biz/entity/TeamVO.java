package guda.ball.biz.entity;

import guda.ball.dao.domain.TeamDO;
import guda.ball.util.enums.BooleanEnum;

/**
 * Created by foodoon on 2015/1/21.
 */
public class TeamVO extends TeamDO {

    private String canJoinCN;

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
    }
}
