package guda.ball.web.form;

import guda.ball.dao.domain.TeamApplyDO;


public class TeamApplyEditForm extends TeamApplyForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamApplyDO toDO(){
        TeamApplyDO teamApplyDO  =super.toDO();
        teamApplyDO.setId(this.id);
        return teamApplyDO;
    }

    public void initForm(TeamApplyDO teamApplyDO){
        if(teamApplyDO == null){
        return ;
    }
    this.setApplyMsg(teamApplyDO.getApplyMsg());
    this.setStatus(teamApplyDO.getStatus());
    this.setUserId(teamApplyDO.getUserId());
    this.setTeamId(teamApplyDO.getTeamId());
}

}
