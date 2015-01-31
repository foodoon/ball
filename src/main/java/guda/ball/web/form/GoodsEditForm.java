package guda.ball.web.form;

import guda.ball.dao.domain.GoodsDO;


public class GoodsEditForm extends GoodsForm{

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsDO toDO(){
        GoodsDO goodsDO  =super.toDO();
        goodsDO.setId(this.id);
        return goodsDO;
    }

    public void initForm(GoodsDO goodsDO){
        if(goodsDO == null){
        return ;
    }
    this.setCourtId(goodsDO.getCourtId());
    this.setPrice(goodsDO.getPrice());
    this.setGoodsDesc(goodsDO.getGoodsDesc());
    this.setGoodsName(goodsDO.getGoodsName());
}

}
