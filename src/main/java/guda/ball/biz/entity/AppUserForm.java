package guda.ball.biz.entity;

import guda.ball.dao.domain.UserDO;


/**
 * Created by foodoon on 2014/8/1.
 */
public class AppUserForm  extends UserDO{


    private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }


}
