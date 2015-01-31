package guda.ball.biz.service;

import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.TeamDO;

/**
 * Created by foodoon on 2014/7/30.
 */
public interface TeamService {

    public BizResult apply(String sid, long teamId);

    public BizResult cancelApply(String sid, long applyId);

    public BizResult create(String sid, TeamDO teamDO);

    public BizResult update(String sid, TeamDO teamDO);

    public BizResult delete(String sid, long id);

    public BizResult queryMyTeamInfo(String sid);
    public BizResult queryMyTeamList(String sid);

    public BizResult queryMyApplyList(String sid, int pageNo, int pageSize);

    public BizResult passApply(String sid, long applyId);

    public BizResult rejectApply(String sid, long applyId);

    public BizResult queryApplyListForReview(String sid, int pageNo, int pageSize);

    public BizResult removeMember(String sid, long removeUserId);

    public BizResult queryMemberList(String sid, long teamId ,int pageNo, int pageSize);

    public BizResult queryTeamList(String sid, int pageNo, int pageSize);

    public BizResult queryTeamInfo(String sid,long teamId);

}
