package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class ChallengeDO {
    private Long id;

    @GenField(cn="约战球队ID",order=1,inSearchForm = false,canNull = false)
    private Long requestTeamId;
    @GenField(cn="应战球队ID",order=1,inSearchForm = false,canNull = false)
    private Long applyTeamId;
    @GenField(cn="场地申请ID",order=1,inSearchForm = false,canNull = false)
    private Long courtApplyId;
    @GenField(cn="比赛说明",order=1,inSearchForm = false,canNull = false)
    private String challengeDesc;
    @GenField(cn="比赛结果",order=1,inSearchForm = false,canNull = false)
    private String challengeResult;
    @GenField(cn="进球数量",order=1,inSearchForm = false,canNull = false)
    private Integer goalCount;
    @GenField(cn="状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;
    @GenField(cn="比赛开始时间",order=1,inSearchForm = false,canNull = false)
    private Date startTime;
    @GenField(cn="比赛结束时间",order=1,inSearchForm = false,canNull = false)
    private Date endTime;


    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestTeamId() {
        return requestTeamId;
    }

    public void setRequestTeamId(Long requestTeamId) {
        this.requestTeamId = requestTeamId;
    }

    public Long getApplyTeamId() {
        return applyTeamId;
    }

    public void setApplyTeamId(Long applyTeamId) {
        this.applyTeamId = applyTeamId;
    }

    public Long getCourtApplyId() {
        return courtApplyId;
    }

    public void setCourtApplyId(Long courtApplyId) {
        this.courtApplyId = courtApplyId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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