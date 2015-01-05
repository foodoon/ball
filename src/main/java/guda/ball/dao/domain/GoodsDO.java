package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class GoodsDO {
    private Integer id;

    @GenField(cn="名称",order=1,inSearchForm = false,canNull = false)
    private String goodsName;

    @GenField(cn="描述",order=1,inSearchForm = false,canNull = false)
    private String goodsDesc;

    @GenField(cn="价格",order=1,inSearchForm = false,canNull = false)
    private Long price;

    @GenField(cn="所在场地",order=1,inSearchForm = false,canNull = false)
    private Integer courtId;

    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getCourtId() {
        return courtId;
    }

    public void setCourtId(Integer courtId) {
        this.courtId = courtId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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