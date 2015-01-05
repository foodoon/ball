package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.CourtApplyDO;

public interface CourtApplyBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(CourtApplyDO courtApplyDO);

        BizResult update(CourtApplyDO courtApplyDO);

}
