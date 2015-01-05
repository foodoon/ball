package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.OrderDO;

public interface OrderBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(OrderDO orderDO);

        BizResult update(OrderDO orderDO);

}
