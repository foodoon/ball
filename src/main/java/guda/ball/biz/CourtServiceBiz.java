package guda.ball.biz;

import guda.ball.dao.domain.CourtServiceDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CourtServiceBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(CourtServiceDO courtServiceDO);

        BizResult update(CourtServiceDO courtServiceDO);

}
