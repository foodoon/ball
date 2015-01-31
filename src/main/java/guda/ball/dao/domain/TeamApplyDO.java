package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamApplyDO {
    private Long id;
    @GenField(cn="球队ID",order=1,inSearchForm = false,canNull = false)
    private Long teamId;
    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;
    @GenField(cn="申请状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    private Date gmtModify;

    private Date gmtCreate;
    @GenField(cn="附加说明",order=1,inSearchForm = false,canNull = false)
    private String applyMsg;

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

    public String getApplyMsg() {
        return applyMsg;
    }

    public void setApplyMsg(String applyMsg) {
        this.applyMsg = applyMsg == null ? null : applyMsg.trim();
    }
}