package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.CourtSiteSectionDO;
import javax.validation.constraints.NotNull;

public class CourtSiteSectionForm {

    public CourtSiteSectionDO toDO(){
       CourtSiteSectionDO courtSiteSectionDO  = new CourtSiteSectionDO();
       return courtSiteSectionDO;
}

}
