package guda.ball.web.form;

import guda.ball.dao.domain.CourtApplyDO;


public class CourtApplyEditForm extends CourtApplyForm{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    this.setUserId(courtApplyDO.getUserId());
    this.setCourtId(courtApplyDO.getCourtId());
    this.setBookingTime(courtApplyDO.getBookingTime());
    this.setStatus(courtApplyDO.getStatus());
}

}
