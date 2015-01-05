package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamDO {
    private Integer id;

    @GenField(cn="球队名",order=1,inSearchForm = false,canNull = false)
    private String name;

    @GenField(cn="简单介绍",order=1,inSearchForm = false,canNull = false)
    private String teamDesc;

    @GenField(cn="是否开放加入",order=1,inSearchForm = false,canNull = false)
    private Integer canJoin;
    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Integer userId;


    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc == null ? null : teamDesc.trim();
    }

    public Integer getCanJoin() {
        return canJoin;
    }

    public void setCanJoin(Integer canJoin) {
        this.canJoin = canJoin;
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