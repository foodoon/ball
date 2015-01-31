package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class UserDO {
    private Long id;
    @GenField(cn="登录名",order=1,inSearchForm = false,canNull = false)
    private String userName;
    @GenField(cn="用户名",order=1,inSearchForm = false,canNull = false)
    private String realName;
    @GenField(cn="邮箱",order=1,inSearchForm = false,canNull = false)
    private String email;
    @GenField(cn="地址",order=1,inSearchForm = false,canNull = false)
    private String address;
    @GenField(cn="密码",order=1,inSearchForm = false,canNull = false)
    private String password;
    @GenField(cn="手机号",order=1,inSearchForm = false,canNull = false)
    private String phone;
    @GenField(cn="喜欢的场地",order=1,inSearchForm = false,canNull = false)
    private String groundTypeOfEnjoy;
    @GenField(cn="特长",order=1,inSearchForm = false,canNull = false)
    private String special;
    @GenField(cn="经常去的场地",order=1,inSearchForm = false,canNull = false)
    private String groundOfDaily;
    @GenField(cn="状态",order=1,inSearchForm = false,canNull = false)
    private Integer status;
    @GenField(cn="头像",order=1,inSearchForm = false,canNull = false)
    private String img;

    private Date gmtModify;

    private Date gmtCreate;
    @GenField(cn="擅长的位置",order=1,inSearchForm = false,canNull = false)
    private String expertLocation;
    @GenField(cn="身高",order=1,inSearchForm = false,canNull = false)
    private String height;
    @GenField(cn="体重",order=1,inSearchForm = false,canNull = false)
    private String weight;
    @GenField(cn="家乡",order=1,inSearchForm = false,canNull = false)
    private String hometown;
    @GenField(cn="擅长哪只脚",order=1,inSearchForm = false,canNull = false)
    private String expertFooter;
    @GenField(cn="个性签名",order=1,inSearchForm = false,canNull = false)
    private String sign;
    @GenField(cn="性别",order=1,inSearchForm = false,canNull = false)
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getExpertLocation() {
        return expertLocation;
    }

    public void setExpertLocation(String expertLocation) {
        this.expertLocation = expertLocation == null ? null : expertLocation.trim();
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown == null ? null : hometown.trim();
    }

    public String getExpertFooter() {
        return expertFooter;
    }

    public void setExpertFooter(String expertFooter) {
        this.expertFooter = expertFooter == null ? null : expertFooter.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }
}