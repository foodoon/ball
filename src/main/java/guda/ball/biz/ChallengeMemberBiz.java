package guda.ball.biz;

import guda.ball.dao.domain.ChallengeMemberDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface ChallengeMemberBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(ChallengeMemberDO challengeMemberDO);

        BizResult update(ChallengeMemberDO challengeMemberDO);

}
