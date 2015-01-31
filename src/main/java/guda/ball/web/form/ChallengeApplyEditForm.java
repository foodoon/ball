package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeApplyDO;


public class ChallengeApplyEditForm extends ChallengeApplyForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChallengeApplyDO toDO(){
        ChallengeApplyDO challengeApplyDO  =super.toDO();
        challengeApplyDO.setId(this.id);
        return challengeApplyDO;
    }

    public void initForm(ChallengeApplyDO challengeApplyDO){
        if(challengeApplyDO == null){
        return ;
    }
    this.setAccept(challengeApplyDO.getAccept());
    this.setChallengeResult(challengeApplyDO.getChallengeResult());
    this.setGoalCount(challengeApplyDO.getGoalCount());
    this.setTeamId(challengeApplyDO.getTeamId());
    this.setChallengeId(challengeApplyDO.getChallengeId());
}

}
