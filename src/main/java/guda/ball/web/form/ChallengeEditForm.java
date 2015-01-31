package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeDO;


public class ChallengeEditForm extends ChallengeForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChallengeDO toDO(){
        ChallengeDO challengeDO  =super.toDO();
        challengeDO.setId(this.id);
        return challengeDO;
    }

    public void initForm(ChallengeDO challengeDO){
        if(challengeDO == null){
        return ;
    }
    this.setEndTime(challengeDO.getEndTime());
    this.setStartTime(challengeDO.getStartTime());
    this.setStatus(challengeDO.getStatus());
    this.setGoalCount(challengeDO.getGoalCount());
    this.setChallengeResult(challengeDO.getChallengeResult());
    this.setChallengeDesc(challengeDO.getChallengeDesc());
    this.setCourtApplyId(challengeDO.getCourtApplyId());
    this.setApplyTeamId(challengeDO.getApplyTeamId());
    this.setRequestTeamId(challengeDO.getRequestTeamId());
}

}
