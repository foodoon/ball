package guda.ball.web.form;

import guda.ball.dao.domain.CourtSiteDO;


public class CourtSiteEditForm extends CourtSiteForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourtSiteDO toDO(){
        CourtSiteDO courtSiteDO  =super.toDO();
        courtSiteDO.setId(this.id);
        return courtSiteDO;
    }

    public void initForm(CourtSiteDO courtSiteDO){
        if(courtSiteDO == null){
        return ;
    }
    this.setOpenTemplate(courtSiteDO.getOpenTemplate());
    this.setOpen(courtSiteDO.getOpen());
    this.setSiteType(courtSiteDO.getSiteType());
    this.setSiteName(courtSiteDO.getSiteName());
    this.setCourtId(courtSiteDO.getCourtId());
}

}
