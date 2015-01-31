package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CourtApplyDO {
    private Long id;


    @GenField(cn="预定用户ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;
    @GenField(cn="预定场馆ID",order=1,inSearchForm = false,canNull = false)
    private Long courtId;

    @GenField(cn="状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    @GenField(cn="预定场地ID",order=1,inSearchForm = false,canNull = false)
    private Long courtSiteId;
    @GenField(cn="预定时间",order=1,inSearchForm = false,canNull = false)
    private String bookingTime;
    @GenField(cn="预定日期",order=1,inSearchForm = false,canNull = false)
    private Date bookingDate;


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

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public Long getCourtSiteId() {
        return courtSiteId;
    }

    public void setCourtSiteId(Long courtSiteId) {
        this.courtSiteId = courtSiteId;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime == null ? null : bookingTime.trim();
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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