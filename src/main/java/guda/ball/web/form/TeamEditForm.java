package guda.ball.web.form;

import guda.ball.dao.domain.TeamDO;


public class TeamEditForm extends TeamForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    this.setJerseyColor(teamDO.getJerseyColor());
    this.setHomeCourt(teamDO.getHomeCourt());
    this.setArea(teamDO.getArea());
    this.setCanJoin(teamDO.getCanJoin());
    this.setTeamType(teamDO.getTeamType());
    this.setTeamDesc(teamDO.getTeamDesc());
    this.setName(teamDO.getName());
    this.setUserId(teamDO.getUserId());
}

}
