package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.ChallengeDO;
import javax.validation.constraints.NotNull;

public class ChallengeForm {
                    @NotNull     private Date endTime;

                    @NotNull     private Date startTime;

                    @NotNull     private Integer status;

                    @NotNull     private Integer goalCount;

                    @NotEmpty(message = "{不能为空}")
            private String challengeResult;

                    @NotEmpty(message = "{不能为空}")
            private String challengeDesc;

                    @NotNull     private Long courtApplyId;

                    @NotNull     private Long applyTeamId;

                    @NotNull     private Long requestTeamId;

    public Date getEndTime() {
       return endTime;
    }

    public void setEndTime(Date endTime) {
       this.endTime = endTime;
    }
    public Date getStartTime() {
       return startTime;
    }

    public void setStartTime(Date startTime) {
       this.startTime = startTime;
    }
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
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
    public String getChallengeDesc() {
       return challengeDesc;
    }

    public void setChallengeDesc(String challengeDesc) {
       this.challengeDesc = challengeDesc;
    }
    public Long getCourtApplyId() {
       return courtApplyId;
    }

    public void setCourtApplyId(Long courtApplyId) {
       this.courtApplyId = courtApplyId;
    }
    public Long getApplyTeamId() {
       return applyTeamId;
    }

    public void setApplyTeamId(Long applyTeamId) {
       this.applyTeamId = applyTeamId;
    }
    public Long getRequestTeamId() {
       return requestTeamId;
    }

    public void setRequestTeamId(Long requestTeamId) {
       this.requestTeamId = requestTeamId;
    }

    public ChallengeDO toDO(){
       ChallengeDO challengeDO  = new ChallengeDO();
            challengeDO.setEndTime(this.endTime);
                challengeDO.setStartTime(this.startTime);
                challengeDO.setStatus(this.status);
                challengeDO.setGoalCount(this.goalCount);
                challengeDO.setChallengeResult(this.challengeResult);
                challengeDO.setChallengeDesc(this.challengeDesc);
                challengeDO.setCourtApplyId(this.courtApplyId);
                challengeDO.setApplyTeamId(this.applyTeamId);
                challengeDO.setRequestTeamId(this.requestTeamId);
           return challengeDO;
}

}
