package guda.ball.web.form;

import guda.ball.dao.domain.UserDO;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserForm {
    @NotEmpty(message = "{不能为空}")
    private String userName;

    @NotEmpty(message = "{不能为空}")
    private String realName;

    @NotEmpty(message = "{不能为空}")
    private String email;

    @NotEmpty(message = "{不能为空}")
    private String address;

    @NotEmpty(message = "{不能为空}")
    private String password;

    @NotEmpty(message = "{不能为空}")
    private String phone;

    @NotEmpty(message = "{不能为空}")
    private String groundTypeOfEnjoy;

    @NotEmpty(message = "{不能为空}")
    private String special;

    @NotEmpty(message = "{不能为空}")
    private String groundOfDaily;

    @NotNull
    private Integer status;

    @NotEmpty(message = "{不能为空}")
    private String img;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroundTypeOfEnjoy() {
        return groundTypeOfEnjoy;
    }

    public void setGroundTypeOfEnjoy(String groundTypeOfEnjoy) {
        this.groundTypeOfEnjoy = groundTypeOfEnjoy;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getGroundOfDaily() {
        return groundOfDaily;
    }

    public void setGroundOfDaily(String groundOfDaily) {
        this.groundOfDaily = groundOfDaily;
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
        this.img = img;
    }

    public UserDO toDO() {
        UserDO userDO = new UserDO();
        userDO.setUserName(this.userName);
        userDO.setRealName(this.realName);
        userDO.setEmail(this.email);
        userDO.setAddress(this.address);
        userDO.setPassword(this.password);
        userDO.setPhone(this.phone);
        userDO.setGroundTypeOfEnjoy(this.groundTypeOfEnjoy);
        userDO.setSpecial(this.special);
        userDO.setGroundOfDaily(this.groundOfDaily);
        userDO.setStatus(this.status);
        userDO.setImg(this.img);
        return userDO;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }

}
