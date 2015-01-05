package guda.ball.biz;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.TeamRecruitDO;

public interface TeamRecruitBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(TeamRecruitDO teamRecruitDO);

        BizResult update(TeamRecruitDO teamRecruitDO);

}
