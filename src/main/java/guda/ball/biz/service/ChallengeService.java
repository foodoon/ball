package guda.ball.biz.service;


import guda.tools.web.page.BizResult;

/**
 * Created by foodoon on 2014/7/31.
 */
public interface ChallengeService {


    public BizResult create(String sid, String userIds,String siteNo,String challengeDesc);


    public BizResult delete(String sid, long id);

    public BizResult queryListForApply(String sid, int pageNo, int pageSize);

    public BizResult queryMyChallengeList(String sid, int pageNo, int pageSize);

    public BizResult queryMyApplyList(String sid, int pageNo, int pageSize);


    public BizResult apply(String sid, long challengeId,String userIds);

    public BizResult cancelApply(String sid, long challengeApplyId);

    public BizResult passApply(String sid, long challengeApplyId);

    public BizResult rejectApply(String sid, long challengeApplyId);

    public BizResult queryChallengeCount(String sid);

    public BizResult queryWaitStartChallengeList(String sid);
    public BizResult queryOverChallengeList(String sid);
    public BizResult queryOngoingChallengeList(String sid);

}
