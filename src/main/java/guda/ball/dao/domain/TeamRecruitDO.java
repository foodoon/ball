package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamRecruitDO {
    private Long id;

    @GenField(cn="发布用户ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;

    @GenField(cn="招募宣言",order=1,inSearchForm = false,canNull = false)
    private String recruitDesc;

    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Long teamId;


    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getRecruitDesc() {
        return recruitDesc;
    }

    public void setRecruitDesc(String recruitDesc) {
        this.recruitDesc = recruitDesc == null ? null : recruitDesc.trim();
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