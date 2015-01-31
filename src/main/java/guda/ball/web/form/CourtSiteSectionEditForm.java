package guda.ball.web.form;

import guda.ball.dao.domain.CourtSiteSectionDO;


public class CourtSiteSectionEditForm extends CourtSiteSectionForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourtSiteSectionDO toDO(){
        CourtSiteSectionDO courtSiteSectionDO  =super.toDO();
        courtSiteSectionDO.setId(this.id);
        return courtSiteSectionDO;
    }

    public void initForm(CourtSiteSectionDO courtSiteSectionDO){
        if(courtSiteSectionDO == null){
        return ;
    }
}

}
