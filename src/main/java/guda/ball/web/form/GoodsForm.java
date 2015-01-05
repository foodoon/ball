package guda.ball.web.form;

import guda.ball.dao.domain.GoodsDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class GoodsForm {
    @NotEmpty(message = "{不能为空}")
    private String goodsName;

    @NotEmpty(message = "{不能为空}")
    private String goodsDesc;

    @NotNull
    private Long price;

    @NotNull
    private Integer courtId;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public GoodsDO toDO() {
        GoodsDO goodsDO = new GoodsDO();
        goodsDO.setGoodsName(this.goodsName);
        goodsDO.setGoodsDesc(this.goodsDesc);
        goodsDO.setPrice(this.price);
        goodsDO.setCourtId(this.courtId);
        return goodsDO;
    }

}
