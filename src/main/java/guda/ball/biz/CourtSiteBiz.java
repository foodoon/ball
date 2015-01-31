package guda.ball.biz;

import guda.ball.biz.query.CourtSiteQuery;
import guda.ball.dao.domain.CourtSiteDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CourtSiteBiz {

        BizResult detail(long id);

        BizResult list(CourtSiteQuery courtSiteQuery);

        BizResult delete(long id);

        BizResult create(CourtSiteDO courtSiteDO);

        BizResult update(CourtSiteDO courtSiteDO);

}
