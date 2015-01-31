package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class ChallengeApplyDO {
    private Long id;

    @GenField(cn="约战ID",order=1,inSearchForm = false,canNull = false)
    private Long challengeId;

    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Long teamId;

    @GenField(cn="比赛进球数",order=1,inSearchForm = false,canNull = false)
    private Integer goalCount;

    @GenField(cn="比赛结果",order=1,inSearchForm = false,canNull = false)
    private String challengeResult;

    @GenField(cn="是否接受",order=1,inSearchForm = false,canNull = false)
    private Integer accept;

    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.challengeResult = challengeResult == null ? null : challengeResult.trim();
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}