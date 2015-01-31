package guda.ball.biz;

import guda.ball.dao.domain.CourtApplyDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CourtApplyBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(CourtApplyDO courtApplyDO);

        BizResult update(CourtApplyDO courtApplyDO);

}
