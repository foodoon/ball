package guda.ball.biz;



import guda.ball.dao.domain.ChallengeDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface ChallengeBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(ChallengeDO challengeDO);

        BizResult update(ChallengeDO challengeDO);

}
