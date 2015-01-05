package guda.ball.web.form;

import guda.ball.dao.domain.TeamApplyDO;

import javax.validation.constraints.NotNull;


public class TeamApplyForm {
    @NotNull
    private Integer teamId;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer status;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public TeamApplyDO toDO() {
        TeamApplyDO teamApplyDO = new TeamApplyDO();
        teamApplyDO.setTeamId(this.teamId);
        teamApplyDO.setUserId(this.userId);
        teamApplyDO.setStatus(this.status);
        return teamApplyDO;
    }

}
