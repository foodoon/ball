package guda.ball.biz.query;

import guda.tools.web.page.BaseQuery;

/**
 * Created by foodoon on 2015/1/31.
 */
public class CourtSiteQuery extends BaseQuery {

    private long courtId;

    public long getCourtId() {
        return courtId;
    }

    public void setCourtId(long courtId) {
        this.courtId = courtId;
    }
}
