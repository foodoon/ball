package guda.ball.biz.entity;

import guda.ball.dao.domain.CourtDO;

import java.util.List;

/**
 * Created by foodoon on 2015/1/30.
 */
public class CourtVO {

    private CourtDO court;

    private List<CourtSiteVO> courtSiteList;

    public CourtDO getCourt() {
        return court;
    }

    public void setCourt(CourtDO court) {
        this.court = court;
    }

    public List<CourtSiteVO> getCourtSiteList() {
        return courtSiteList;
    }

    public void setCourtSiteList(List<CourtSiteVO> courtSiteList) {
        this.courtSiteList = courtSiteList;
    }
}
