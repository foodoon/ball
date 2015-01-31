package guda.ball.dao.domain;

import java.util.Date;

public class ChallengeApplyMemberDO {
    private Long id;

    private Long challengeApplyId;

    private Long challengeId;

    private Long teamId;

    private Long userId;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChallengeApplyId() {
        return challengeApplyId;
    }

    public void setChallengeApplyId(Long challengeApplyId) {
        this.challengeApplyId = challengeApplyId;
    }

    public Long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Long challengeId) {
        this.challengeId = challengeId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}