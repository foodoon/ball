package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ChallengeForm {
                    @NotNull     private Integer teamId;

                    @NotNull     private Integer courtId;

                    @NotNull     private Date challengeTime;

                    @NotEmpty(message = "{不能为空}")
            private String challengeDesc;

                    @NotEmpty(message = "{不能为空}")
            private String challengeResult;

                    @NotNull     private Integer goalCount;

                    @NotNull     private Integer status;

    public Integer getTeamId() {
       return teamId;
    }

    public void setTeamId(Integer teamId) {
       this.teamId = teamId;
    }
    public Integer getCourtId() {
       return courtId;
    }

    public void setCourtId(Integer courtId) {
       this.courtId = courtId;
    }
    public Date getChallengeTime() {
       return challengeTime;
    }

    public void setChallengeTime(Date challengeTime) {
       this.challengeTime = challengeTime;
    }
    public String getChallengeDesc() {
       return challengeDesc;
    }

    public void setChallengeDesc(String challengeDesc) {
       this.challengeDesc = challengeDesc;
    }
    public String getChallengeResult() {
       return challengeResult;
    }

    public void setChallengeResult(String challengeResult) {
       this.challengeResult = challengeResult;
    }
    public Integer getGoalCount() {
       return goalCount;
    }

    public void setGoalCount(Integer goalCount) {
       this.goalCount = goalCount;
    }
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }

    public ChallengeDO toDO(){
       ChallengeDO challengeDO  = new ChallengeDO();
            challengeDO.setTeamId(this.teamId);
                challengeDO.setCourtId(this.courtId);
                challengeDO.setChallengeTime(this.challengeTime);
                challengeDO.setChallengeDesc(this.challengeDesc);
                challengeDO.setChallengeResult(this.challengeResult);
                challengeDO.setGoalCount(this.goalCount);
                challengeDO.setStatus(this.status);
           return challengeDO;
}

}
