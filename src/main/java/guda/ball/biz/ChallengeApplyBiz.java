package guda.ball.biz;

//guda.tools.web.page.BaseQuery;
//import guda.tools.web.page.BizResul;
import guda.ball.dao.domain.ChallengeApplyDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface ChallengeApplyBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(ChallengeApplyDO challengeApplyDO);

        BizResult update(ChallengeApplyDO challengeApplyDO);

}
