package guda.ball.biz;

import guda.ball.dao.domain.CourtSiteSectionDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CourtSiteSectionBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(CourtSiteSectionDO courtSiteSectionDO);

        BizResult update(CourtSiteSectionDO courtSiteSectionDO);

}
