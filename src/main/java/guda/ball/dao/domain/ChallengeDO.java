package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class ChallengeDO {
    private Integer id;

    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Integer teamId;

    @GenField(cn="场地ID",order=1,inSearchForm = false,canNull = false)
    private Integer courtId;

    @GenField(cn="约战日期",order=1,inSearchForm = false,canNull = false)
    private Date challengeTime;

    @GenField(cn="约战条件",order=1,inSearchForm = false,canNull = false)
    private String challengeDesc;

    @GenField(cn="约战结果",order=1,inSearchForm = false,canNull = false)
    private String challengeResult;

    @GenField(cn="比赛进球数",order=1,inSearchForm = false,canNull = false)
    private Integer goalCount;

    @GenField(cn="是否接受应战",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.challengeDesc = challengeDesc == null ? null : challengeDesc.trim();
    }

    public String getChallengeResult() {
        return challengeResult;
    }

    public void setChallengeResult(String challengeResult) {
        this.challengeResult = challengeResult == null ? null : challengeResult.trim();
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