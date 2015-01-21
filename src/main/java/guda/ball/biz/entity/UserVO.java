package guda.ball.biz.entity;

import guda.ball.dao.domain.UserDO;
import guda.ball.util.enums.CourtTypeEnum;
import guda.gen.GenField;

/**
 * Created by foodoon on 2015/1/21.
 */
public class UserVO {

    private Integer id;
    private String userName;
    private String realName;
    private String email;
    private String address;
    private String phone;
    private String groundTypeOfEnjoy;
    private String special;
    private String groundOfDaily;
    private String img;


    private String groundTypeOfEnjoyString;


    public UserVO(){

    }

    public UserVO(UserDO userDO){
        this.groundTypeOfEnjoy = userDO.getGroundTypeOfEnjoy();
        this.address = userDO.getAddress();
        this.email = userDO.getEmail();
        this.phone = userDO.getPhone();
        this.groundOfDaily = userDO.getGroundOfDaily();
        this.img  = userDO.getImg();
        this.userName = userDO.getUserName();
        this.realName = userDO.getRealName();
        this.special = userDO.getSpecial();
        this.id = userDO.getId();
        CourtTypeEnum byValue = CourtTypeEnum.getByValue(getGroundTypeOfEnjoy());
        if(byValue!=null) {
            this.groundTypeOfEnjoyString =byValue.msg;
        }


    }

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGroundTypeOfEnjoyString() {
        return groundTypeOfEnjoyString;
    }

    public void setGroundTypeOfEnjoyString(String groundTypeOfEnjoyString) {
        this.groundTypeOfEnjoyString = groundTypeOfEnjoyString;
    }


}
