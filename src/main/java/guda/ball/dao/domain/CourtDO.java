package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CourtDO {
    private Long id;
    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;
    @GenField(cn="名称",order=1,inSearchForm = false,canNull = false)
    private String name;
    @GenField(cn="地址",order=1,inSearchForm = false,canNull = false)
    private String address;
    @GenField(cn="联系人",order=1,inSearchForm = false,canNull = false)
    private String contactName;
    @GenField(cn="联系电话",order=1,inSearchForm = false,canNull = false)
    private String tel;
    @GenField(cn="场地状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;
    @GenField(cn="场地描述",order=1,inSearchForm = false,canNull = false)
    private String courtDesc;

    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourtDesc() {
        return courtDesc;
    }

    public void setCourtDesc(String courtDesc) {
        this.courtDesc = courtDesc == null ? null : courtDesc.trim();
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