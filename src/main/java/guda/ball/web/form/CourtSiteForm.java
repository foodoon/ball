package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.CourtSiteDO;
import javax.validation.constraints.NotNull;

public class CourtSiteForm {
                    @NotEmpty(message = "{不能为空}")
            private String openTemplate;

                    @NotNull     private Integer open;

                    @NotEmpty(message = "{不能为空}")
            private String siteType;

                    @NotEmpty(message = "{不能为空}")
            private String siteName;

                    @NotNull     private Long courtId;

    public String getOpenTemplate() {
       return openTemplate;
    }

    public void setOpenTemplate(String openTemplate) {
       this.openTemplate = openTemplate;
    }
    public Integer getOpen() {
       return open;
    }

    public void setOpen(Integer open) {
       this.open = open;
    }
    public String getSiteType() {
       return siteType;
    }

    public void setSiteType(String siteType) {
       this.siteType = siteType;
    }
    public String getSiteName() {
       return siteName;
    }

    public void setSiteName(String siteName) {
       this.siteName = siteName;
    }
    public Long getCourtId() {
       return courtId;
    }

    public void setCourtId(Long courtId) {
       this.courtId = courtId;
    }

    public CourtSiteDO toDO(){
       CourtSiteDO courtSiteDO  = new CourtSiteDO();
            courtSiteDO.setOpenTemplate(this.openTemplate);
                courtSiteDO.setOpen(this.open);
                courtSiteDO.setSiteType(this.siteType);
                courtSiteDO.setSiteName(this.siteName);
                courtSiteDO.setCourtId(this.courtId);
           return courtSiteDO;
}

}
