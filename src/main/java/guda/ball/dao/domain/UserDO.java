package guda.ball.dao.domain;

import java.util.Date;

public class UserDO {
    private Integer id;

    private String userName;

    private String realName;

    private String email;

    private String address;

    private String password;

    private String phone;

    private String groundTypeOfEnjoy;

    private String special;

    private String groundOfDaily;

    private Integer status;

    private String img;

    private Date gmtModify;

    private Date gmtCreate;

    private String expertLocation;

    private String height;

    private String weight;

    private String hometown;

    private String expertFooter;

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
}