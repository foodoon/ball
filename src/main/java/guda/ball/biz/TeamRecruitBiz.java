package guda.ball.biz;

import guda.ball.dao.domain.TeamRecruitDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface TeamRecruitBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(TeamRecruitDO teamRecruitDO);

        BizResult update(TeamRecruitDO teamRecruitDO);

}
