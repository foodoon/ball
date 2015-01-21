package guda.ball.biz;

import guda.ball.dao.domain.TeamApplyDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TeamApplyBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TeamApplyDO teamApplyDO);

        BizResult update(TeamApplyDO teamApplyDO);

}
