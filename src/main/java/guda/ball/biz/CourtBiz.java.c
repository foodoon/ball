package guda.ball.biz;

import guda.ball.dao.domain.CourtDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CourtBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(CourtDO courtDO);

        BizResult update(CourtDO courtDO);

}
