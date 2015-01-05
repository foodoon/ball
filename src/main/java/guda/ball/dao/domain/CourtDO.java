package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CourtDO {
    private Integer id;

    @GenField(cn="球场名称",order=1,inSearchForm = false,canNull = false)
    private String name;

    @GenField(cn="地址",order=1,inSearchForm = false,canNull = false)
    private String address;

    @GenField(cn="类型",order=1,inSearchForm = false,canNull = false)
    private String type;

    @GenField(cn="开放时间",order=1,inSearchForm = false,canNull = false)
    private String openTime;


    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Integer userId;

    private Integer isDeleted;

    @GenField(cn="是否开放",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    @GenField(cn="球场介绍",order=1,inSearchForm = false,canNull = false)
    private String courtDesc;

    @GenField(cn="面积",order=1,inSearchForm = false,canNull = false)
    private String square;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square == null ? null : square.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourtDesc() {
        return courtDesc;
    }

    public void setCourtDesc(String desc) {
        this.courtDesc = desc == null ? null : desc.trim();
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