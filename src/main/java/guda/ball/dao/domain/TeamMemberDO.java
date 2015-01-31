package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamMemberDO {
    private Long id;
    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Long teamId;
    @GenField(cn="球员ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;

    private Date gmtCreate;

    private Date gmtModify;
    @GenField(cn="是否创建者",order=1,inSearchForm = false,canNull = false)
    private Integer creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
}