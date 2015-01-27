package guda.ball.biz.service;
import guda.tools.web.page.BizResult;
import guda.ball.dao.domain.CourtDO;

/**
 * Created by foodoon on 2014/7/31.
 */
public interface CourtService {

    public BizResult create(String sid, CourtDO courtDO);

    public BizResult update(String sid, CourtDO courtDO);

    public BizResult delete(String sid, int id);

    public BizResult apply(String sid, int courtId, String applyTime);

    public BizResult cancelApply(String sid, int applyId);

    public BizResult passApply(String sid, int applyId);

    public BizResult rejectApply(String sid, int applyId);

    public BizResult queryBookingList(String sid, int pageNo, int pageSize);

    public BizResult queryBookingListForReview(String sid, int pageNo, int pageSize);

    public BizResult queryList(String sid, int pageNo, int pageSize);

    public BizResult queryListByName(String sid, String name,int pageNo, int pageSize);

}
