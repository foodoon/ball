package guda.ball.biz.entity;

import guda.ball.dao.domain.CourtSiteSectionDO;

import java.util.List;

/**
 * Created by foodoon on 2015/1/31.
 */
public class OpenTemplate {

    private int week;
    private List<CourtSiteSectionDO> timeSection;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public List<CourtSiteSectionDO> getTimeSection() {
        return timeSection;
    }

    public void setTimeSection(List<CourtSiteSectionDO> timeSection) {
        this.timeSection = timeSection;
    }
}
