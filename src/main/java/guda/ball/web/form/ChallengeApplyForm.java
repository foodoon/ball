package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeApplyDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class ChallengeApplyForm {
    @NotNull
    private Integer accept;

    @NotEmpty(message = "{不能为空}")
    private String challengeResult;

    @NotNull
    private Integer goalCount;

    @NotNull
    private Long teamId;

    @NotNull
    private Long challengeId;

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
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

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public ChallengeApplyDO toDO() {
        ChallengeApplyDO challengeApplyDO = new ChallengeApplyDO();
        challengeApplyDO.setAccept(this.accept);
        challengeApplyDO.setChallengeResult(this.challengeResult);
        challengeApplyDO.setGoalCount(this.goalCount);
        challengeApplyDO.setTeamId(this.teamId);
        challengeApplyDO.setChallengeId(this.challengeId);
        return challengeApplyDO;
    }

}
