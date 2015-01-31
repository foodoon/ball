package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class TeamDO {
    private Long id;

    @GenField(cn="创建人ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;
    @GenField(cn="球队名称",order=1,inSearchForm = false,canNull = false)
    private String name;
    @GenField(cn="球队口号",order=1,inSearchForm = false,canNull = false)
    private String teamDesc;
    @GenField(cn="球队类型",order=1,inSearchForm = false,canNull = false)
    private String teamType;
    @GenField(cn="是否开放加入",order=1,inSearchForm = false,canNull = false)
    private Integer canJoin;

    private Date gmtModify;

    private Date gmtCreate;
    @GenField(cn="球队活动区域",order=1,inSearchForm = false,canNull = false)
    private String area;
    @GenField(cn="主场",order=1,inSearchForm = false,canNull = false)
    private String homeCourt;
    @GenField(cn="球服颜色",order=1,inSearchForm = false,canNull = false)
    private String jerseyColor;

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

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType == null ? null : teamType.trim();
    }

    public Integer getCanJoin() {
        return canJoin;
    }

    public void setCanJoin(Integer canJoin) {
        this.canJoin = canJoin;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getHomeCourt() {
        return homeCourt;
    }

    public void setHomeCourt(String homeCourt) {
        this.homeCourt = homeCourt == null ? null : homeCourt.trim();
    }

    public String getJerseyColor() {
        return jerseyColor;
    }

    public void setJerseyColor(String jerseyColor) {
        this.jerseyColor = jerseyColor == null ? null : jerseyColor.trim();
    }
}