package guda.ball.web.form;

import guda.ball.dao.domain.ChallengeMemberDO;


public class ChallengeMemberEditForm extends ChallengeMemberForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChallengeMemberDO toDO(){
        ChallengeMemberDO challengeMemberDO  =super.toDO();
        challengeMemberDO.setId(this.id);
        return challengeMemberDO;
    }

    public void initForm(ChallengeMemberDO challengeMemberDO){
        if(challengeMemberDO == null){
        return ;
    }
}

}
