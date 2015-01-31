package guda.ball.dao.domain;

import guda.gen.GenField;

public class CourtSiteDO {
    private Long id;
    @GenField(cn="场地ID",order=1,inSearchForm = false,canNull = false)
    private Long courtId;
    @GenField(cn="场地名称",order=1,inSearchForm = false,canNull = false)
    private String siteName;
    @GenField(cn="场地类型",order=1,inSearchForm = false,canNull = false)
    private String siteType;
    @GenField(cn="是否开放",order=1,inSearchForm = false,canNull = false)
    private Integer open;
    @GenField(cn="场地开放模版",order=1,inSearchForm = false,canNull = false)
    private String openTemplate;

    private String price;

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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType == null ? null : siteType.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public String getOpenTemplate() {
        return openTemplate;
    }

    public void setOpenTemplate(String openTemplate) {
        this.openTemplate = openTemplate == null ? null : openTemplate.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }
}