package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.SessionDO;

public interface SessionBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(SessionDO sessionDO);

        BizResult update(SessionDO sessionDO);

        SessionDO querySessionBySID(String sid);

        BizResult checkSession(String sid);



}
