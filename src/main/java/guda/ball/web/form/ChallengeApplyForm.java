package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeApplyDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ChallengeApplyForm {
                    @NotNull     private Integer challengeId;

                    @NotNull     private Integer teamId;

                    @NotNull     private Integer goalCount;

                    @NotEmpty(message = "{不能为空}")
            private String challengeResult;

                    @NotNull     private Integer accept;

    public Integer getChallengeId() {
       return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
       this.challengeId = challengeId;
    }
    public Integer getTeamId() {
       return teamId;
    }

    public void setTeamId(Integer teamId) {
       this.teamId = teamId;
    }
    public Integer getGoalCount() {
       return goalCount;
    }

    public void setGoalCount(Integer goalCount) {
       this.goalCount = goalCount;
    }
    public String getChallengeResult() {
       return challengeResult;
    }

    public void setChallengeResult(String challengeResult) {
       this.challengeResult = challengeResult;
    }
    public Integer getAccept() {
       return accept;
    }

    public void setAccept(Integer accept) {
       this.accept = accept;
    }

    public ChallengeApplyDO toDO(){
       ChallengeApplyDO challengeApplyDO  = new ChallengeApplyDO();
            challengeApplyDO.setChallengeId(this.challengeId);
                challengeApplyDO.setTeamId(this.teamId);
                challengeApplyDO.setGoalCount(this.goalCount);
                challengeApplyDO.setChallengeResult(this.challengeResult);
                challengeApplyDO.setAccept(this.accept);
           return challengeApplyDO;
}

}
