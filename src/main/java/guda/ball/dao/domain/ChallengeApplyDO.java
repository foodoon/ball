package guda.ball.dao.domain;



import guda.gen.GenField;

import java.util.Date;

public class ChallengeApplyDO {
    private Integer id;

    @GenField(cn="约战ID",order=1,inSearchForm = false,canNull = false)
    private Integer challengeId;

    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Integer teamId;

    @GenField(cn="比赛进球数",order=1,inSearchForm = false,canNull = false)
    private Integer goalCount;

    @GenField(cn="比赛结果",order=1,inSearchForm = false,canNull = false)
    private String challengeResult;

    @GenField(cn="是否接受",order=1,inSearchForm = false,canNull = false)
    private Integer accept;

    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.challengeResult = challengeResult == null ? null : challengeResult.trim();
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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