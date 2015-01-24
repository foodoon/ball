package guda.ball.biz;

import guda.ball.dao.domain.TeamApplyDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TeamApplyBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(TeamApplyDO teamApplyDO);

        BizResult update(TeamApplyDO teamApplyDO);

}
