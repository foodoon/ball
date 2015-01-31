package guda.ball.biz;

import guda.ball.dao.domain.TeamDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TeamBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TeamDO teamDO);

        BizResult update(TeamDO teamDO);

}
