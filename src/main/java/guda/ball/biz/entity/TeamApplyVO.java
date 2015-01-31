package guda.ball.biz.entity;

import guda.ball.dao.domain.TeamApplyDO;
import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.UserDO;
import guda.ball.util.enums.ApplyStatusEnum;


/**
 * Created by foodoon on 2014/7/30.
 */
public class TeamApplyVO extends TeamApplyDO {

    private TeamDO team;
    private UserDO applyUser;
    public TeamApplyVO() {
    }

    public TeamApplyVO(TeamApplyDO teamApplyDO) {
        this.setId(teamApplyDO.getId());
        this.setTeamId(teamApplyDO.getTeamId());
        this.setUserId(teamApplyDO.getUserId());
        this.setStatus(teamApplyDO.getStatus());
        this.setGmtModify(teamApplyDO.getGmtModify());
        this.setGmtCreate(teamApplyDO.getGmtCreate());
        ApplyStatusEnum byValue = ApplyStatusEnum.getByValue(teamApplyDO.getStatus());
        if(byValue!=null) {
            this.statusCN =byValue.msg;
        }
    }

    public TeamDO getTeam() {
        return team;
    }

    public void setTeam(TeamDO team) {
        this.team = team;
    }

    public UserDO getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(UserDO applyUser) {
        this.applyUser = applyUser;
    }

    private String applyUserName;

    private String applyRealName;

    private String statusCN;

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getApplyRealName() {
        return applyRealName;
    }

    public void setApplyRealName(String applyRealName) {
        this.applyRealName = applyRealName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }
}
