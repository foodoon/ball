package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamRecruitDO {
    private Integer id;

    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Integer userId;

    @GenField(cn="招募宣言",order=1,inSearchForm = false,canNull = false)
    private String recruitDesc;

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

    public String getRecruitDesc() {
        return recruitDesc;
    }

    public void setRecruitDesc(String desc) {
        this.recruitDesc = desc == null ? null : desc.trim();
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