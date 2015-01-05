package guda.ball.web.form;

import guda.ball.dao.domain.UserDO;


public class UserEditForm extends UserForm {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDO toDO() {
        UserDO userDO = super.toDO();
        userDO.setId(this.id);
        return userDO;
    }

    public void initForm(UserDO userDO) {
        if (userDO == null) {
            return;
        }
        this.setUserName(userDO.getUserName());
        this.setRealName(userDO.getRealName());
        this.setEmail(userDO.getEmail());
        this.setAddress(userDO.getAddress());
        this.setPassword(userDO.getPassword());
        this.setPhone(userDO.getPhone());
        this.setGroundTypeOfEnjoy(userDO.getGroundTypeOfEnjoy());
        this.setSpecial(userDO.getSpecial());
        this.setGroundOfDaily(userDO.getGroundOfDaily());
        this.setStatus(userDO.getStatus());
        this.setImg(userDO.getImg());
    }

}
