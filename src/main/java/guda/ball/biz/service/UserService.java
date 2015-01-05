package guda.ball.biz.service;

import guda.tools.web.page.BizResult;
import guda.ball.biz.entity.AppUserForm;
import guda.ball.dao.domain.UserDO;

/**
 * Created by foodoon on 2014/7/30.
 */
public interface UserService {


    public BizResult create(AppUserForm appUserForm);

    public BizResult update(String sid, UserDO userDO);

    public BizResult queryUserInfo(String sid);

    public BizResult login(String userName, String password);

    public BizResult loginOut(String sid);


}
