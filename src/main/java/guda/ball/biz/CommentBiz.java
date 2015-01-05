package guda.ball.biz;


import guda.ball.dao.domain.CommentDO;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public interface CommentBiz {

        BizResult detail(int id);

        BizResult list(BaseQuery baseQuery);

        BizResult delete(int id);

        BizResult create(CommentDO commentDO);

        BizResult update(CommentDO commentDO);

}
