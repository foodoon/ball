package guda.ball.biz;

import guda.ball.dao.domain.OrderDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface OrderBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(OrderDO orderDO);

        BizResult update(OrderDO orderDO);

}
