package guda.ball.biz.entity;

import guda.ball.dao.domain.CourtSiteDO;

import java.util.List;

/**
 * Created by foodoon on 2015/1/30.
 */
public class CourtSiteVO {

    private CourtSiteDO courtSite;

    private List<BookInfoVO> bookInfoVOList;

    public CourtSiteDO getCourtSite() {
        return courtSite;
    }

    public void setCourtSite(CourtSiteDO courtSite) {
        this.courtSite = courtSite;
    }

    public List<BookInfoVO> getBookInfoVOList() {
        return bookInfoVOList;
    }

    public void setBookInfoVOList(List<BookInfoVO> bookInfoVOList) {
        this.bookInfoVOList = bookInfoVOList;
    }
}
