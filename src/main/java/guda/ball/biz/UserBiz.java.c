package guda.ball.biz;

import guda.ball.dao.domain.UserDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface UserBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(UserDO userDO);

        BizResult update(UserDO userDO);

}
