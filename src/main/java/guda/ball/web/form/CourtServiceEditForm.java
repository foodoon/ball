package guda.ball.web.form;

import guda.ball.dao.domain.CourtServiceDO;


public class CourtServiceEditForm extends CourtServiceForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourtServiceDO toDO(){
        CourtServiceDO courtServiceDO  =super.toDO();
        courtServiceDO.setId(this.id);
        return courtServiceDO;
    }

    public void initForm(CourtServiceDO courtServiceDO){
        if(courtServiceDO == null){
        return ;
    }
    this.setServiceName(courtServiceDO.getServiceName());
    this.setCourtId(courtServiceDO.getCourtId());
}

}
