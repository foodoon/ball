package guda.ball.web.form;

import guda.ball.dao.domain.GoodsDO;


public class GoodsEditForm extends GoodsForm {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GoodsDO toDO() {
        GoodsDO goodsDO = super.toDO();
        goodsDO.setId(this.id);
        return goodsDO;
    }

    public void initForm(GoodsDO goodsDO) {
        if (goodsDO == null) {
            return;
        }
        this.setGoodsName(goodsDO.getGoodsName());
        this.setGoodsDesc(goodsDO.getGoodsDesc());
        this.setPrice(goodsDO.getPrice());
        this.setCourtId(goodsDO.getCourtId());
    }

}
