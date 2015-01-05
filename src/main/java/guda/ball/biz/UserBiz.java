package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.UserDO;

public interface UserBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(UserDO userDO);

        BizResult update(UserDO userDO);

        BizResult login(String userName, String password);

        BizResult loginOut(String sid);



}
