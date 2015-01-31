package guda.ball.web.form;

import guda.ball.dao.domain.TeamMemberDO;


public class TeamMemberEditForm extends TeamMemberForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamMemberDO toDO(){
        TeamMemberDO teamMemberDO  =super.toDO();
        teamMemberDO.setId(this.id);
        return teamMemberDO;
    }

    public void initForm(TeamMemberDO teamMemberDO){
        if(teamMemberDO == null){
        return ;
    }
    this.setCreator(teamMemberDO.getCreator());
    this.setUserId(teamMemberDO.getUserId());
    this.setTeamId(teamMemberDO.getTeamId());
}

}
