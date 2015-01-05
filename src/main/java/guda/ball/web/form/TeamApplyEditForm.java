package guda.ball.web.form;

import guda.ball.dao.domain.TeamApplyDO;


public class TeamApplyEditForm extends TeamApplyForm {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamApplyDO toDO() {
        TeamApplyDO teamApplyDO = super.toDO();
        teamApplyDO.setId(this.id);
        return teamApplyDO;
    }

    public void initForm(TeamApplyDO teamApplyDO) {
        if (teamApplyDO == null) {
            return;
        }
        this.setTeamId(teamApplyDO.getTeamId());
        this.setUserId(teamApplyDO.getUserId());
        this.setStatus(teamApplyDO.getStatus());
    }

}
