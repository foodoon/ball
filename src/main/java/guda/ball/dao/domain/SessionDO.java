package guda.ball.dao.domain;

import java.util.Date;

public class SessionDO {
    private Long id;

    private Long userId;

    private String sId;

    private Date expireTime;

    private Date gmtCreate;

    private Date gmtModify;

    private String storeData;

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

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId == null ? null : sId.trim();
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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

    public String getStoreData() {
        return storeData;
    }

    public void setStoreData(String storeData) {
        this.storeData = storeData == null ? null : storeData.trim();
    }
}