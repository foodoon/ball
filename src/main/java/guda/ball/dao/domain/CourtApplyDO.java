package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CourtApplyDO {
    private Integer id;

    @GenField(cn="预定用户ID",order=1,inSearchForm = false,canNull = false)
    private Integer userId;
    @GenField(cn="预定场地ID",order=1,inSearchForm = false,canNull = false)
    private Integer courtId;
    @GenField(cn="预定时间",order=1,inSearchForm = false,canNull = false)
    private Date bookingTime;
    @GenField(cn="状态",order=1,inSearchForm = false,canNull = false)
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
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