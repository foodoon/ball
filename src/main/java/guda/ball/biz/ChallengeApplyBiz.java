package guda.ball.biz;

import guda.ball.dao.domain.ChallengeApplyDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface ChallengeApplyBiz {

        BizResult detail(long id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(long id);

        BizResult create(ChallengeApplyDO challengeApplyDO);

        BizResult update(ChallengeApplyDO challengeApplyDO);

}
