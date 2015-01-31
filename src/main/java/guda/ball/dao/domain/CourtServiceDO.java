package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CourtServiceDO {
    private Long id;
    @GenField(cn="场地ID",order=1,inSearchForm = false,canNull = false)
    private Long courtId;
    @GenField(cn="服务名称",order=1,inSearchForm = false,canNull = false)
    private String serviceName;

    private Date gmtCreated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }
}