package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.TeamMemberDO;

public interface TeamMemberBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(TeamMemberDO teamMemberDO);

        BizResult update(TeamMemberDO teamMemberDO);

}
