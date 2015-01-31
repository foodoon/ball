package guda.ball.biz;

import guda.ball.dao.domain.GoodsDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface GoodsBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(GoodsDO goodsDO);

        BizResult update(GoodsDO goodsDO);

}
