package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.ChallengeMemberDO;
import javax.validation.constraints.NotNull;

public class ChallengeMemberForm {

    public ChallengeMemberDO toDO(){
       ChallengeMemberDO challengeMemberDO  = new ChallengeMemberDO();
       return challengeMemberDO;
}

}
