package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class GoodsDO {
    private Long id;

    @GenField(cn="名称",order=1,inSearchForm = false,canNull = false)
    private String goodsName;

    @GenField(cn="描述",order=1,inSearchForm = false,canNull = false)
    private String goodsDesc;

    @GenField(cn="价格",order=1,inSearchForm = false,canNull = false)
    private Long price;

    @GenField(cn="所在场地",order=1,inSearchForm = false,canNull = false)
    private Long courtId;


    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc == null ? null : goodsDesc.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}