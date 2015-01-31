package guda.ball.biz;

import guda.ball.dao.domain.TeamMemberDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TeamMemberBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TeamMemberDO teamMemberDO);

        BizResult update(TeamMemberDO teamMemberDO);

}
