package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.GoodsDO;
import javax.validation.constraints.NotNull;

public class GoodsForm {
                    @NotNull     private Long courtId;

                    @NotNull     private Long price;

                    @NotEmpty(message = "{不能为空}")
            private String goodsDesc;

                    @NotEmpty(message = "{不能为空}")
            private String goodsName;

    public Long getCourtId() {
       return courtId;
    }

    public void setCourtId(Long courtId) {
       this.courtId = courtId;
    }
    public Long getPrice() {
       return price;
    }

    public void setPrice(Long price) {
       this.price = price;
    }
    public String getGoodsDesc() {
       return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
       this.goodsDesc = goodsDesc;
    }
    public String getGoodsName() {
       return goodsName;
    }

    public void setGoodsName(String goodsName) {
       this.goodsName = goodsName;
    }

    public GoodsDO toDO(){
       GoodsDO goodsDO  = new GoodsDO();
            goodsDO.setCourtId(this.courtId);
                goodsDO.setPrice(this.price);
                goodsDO.setGoodsDesc(this.goodsDesc);
                goodsDO.setGoodsName(this.goodsName);
           return goodsDO;
}

}
