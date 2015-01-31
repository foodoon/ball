package guda.ball.web.form;

import guda.ball.dao.domain.CourtDO;


public class CourtEditForm extends CourtForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourtDO toDO(){
        CourtDO courtDO  =super.toDO();
        courtDO.setId(this.id);
        return courtDO;
    }

    public void initForm(CourtDO courtDO){
        if(courtDO == null){
        return ;
    }
    this.setCourtDesc(courtDO.getCourtDesc());
    this.setStatus(courtDO.getStatus());
    this.setTel(courtDO.getTel());
    this.setContactName(courtDO.getContactName());
    this.setAddress(courtDO.getAddress());
    this.setName(courtDO.getName());
    this.setUserId(courtDO.getUserId());
}

}
