package guda.ball.web.form;

import guda.ball.dao.domain.TeamRecruitDO;


public class TeamRecruitEditForm extends TeamRecruitForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamRecruitDO toDO(){
        TeamRecruitDO teamRecruitDO  =super.toDO();
        teamRecruitDO.setId(this.id);
        return teamRecruitDO;
    }

    public void initForm(TeamRecruitDO teamRecruitDO){
        if(teamRecruitDO == null){
        return ;
    }
    this.setTeamId(teamRecruitDO.getTeamId());
    this.setRecruitDesc(teamRecruitDO.getRecruitDesc());
    this.setUserId(teamRecruitDO.getUserId());
}

}
