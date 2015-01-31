package guda.ball.web.form;

import guda.ball.dao.domain.CourtApplyDO;


public class CourtApplyEditForm extends CourtApplyForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourtApplyDO toDO(){
        CourtApplyDO courtApplyDO  =super.toDO();
        courtApplyDO.setId(this.id);
        return courtApplyDO;
    }

    public void initForm(CourtApplyDO courtApplyDO){
        if(courtApplyDO == null){
        return ;
    }
    this.setBookingDate(courtApplyDO.getBookingDate());
    this.setBookingTime(courtApplyDO.getBookingTime());
    this.setCourtSiteId(courtApplyDO.getCourtSiteId());
    this.setStatus(courtApplyDO.getStatus());
    this.setCourtId(courtApplyDO.getCourtId());
    this.setUserId(courtApplyDO.getUserId());
}

}
