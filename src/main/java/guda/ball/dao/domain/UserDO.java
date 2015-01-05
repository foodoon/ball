package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class UserDO {
    private Integer id;

    @GenField(cn="登录名",order=1,inSearchForm = false,canNull = false)
    private String userName;

    @GenField(cn="真实姓名",order=1,inSearchForm = false,canNull = false)
    private String realName;

    @GenField(cn="电子邮箱",order=1,inSearchForm = false,canNull = false)
    private String email;

    @GenField(cn="住址",order=1,inSearchForm = false,canNull = false)
    private String address;

    @GenField(cn="密码",order=1,inSearchForm = false,canNull = false)
    private String password;

    @GenField(cn="手机号码",order=1,inSearchForm = false,canNull = false)
    private String phone;

    @GenField(cn="喜欢的场地类型",order=1,inSearchForm = false,canNull = false)
    private String groundTypeOfEnjoy;

    @GenField(cn="特长",order=1,inSearchForm = false,canNull = false)
    private String special;

    @GenField(cn="经常比赛的场地",order=1,inSearchForm = false,canNull = false)
    private String groundOfDaily;

    @GenField(cn="状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;

    @GenField(cn="头像",order=1,inSearchForm = false,canNull = false)
    private String img;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGroundTypeOfEnjoy() {
        return groundTypeOfEnjoy;
    }

    public void setGroundTypeOfEnjoy(String groundTypeOfEnjoy) {
        this.groundTypeOfEnjoy = groundTypeOfEnjoy == null ? null : groundTypeOfEnjoy.trim();
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public String getGroundOfDaily() {
        return groundOfDaily;
    }

    public void setGroundOfDaily(String groundOfDaily) {
        this.groundOfDaily = groundOfDaily == null ? null : groundOfDaily.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
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