package guda.ball.web.form;

import guda.ball.dao.domain.TeamDO;


public class TeamEditForm extends TeamForm{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamDO toDO(){
        TeamDO teamDO  =super.toDO();
        teamDO.setId(this.id);
        return teamDO;
    }

    public void initForm(TeamDO teamDO){
        if(teamDO == null){
        return ;
    }
    this.setName(teamDO.getName());
    this.setTeamDesc(teamDO.getTeamDesc());
    this.setCanJoin(teamDO.getCanJoin());
    this.setUserId(teamDO.getUserId());
}

}
