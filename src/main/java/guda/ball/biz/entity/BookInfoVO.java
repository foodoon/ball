package guda.ball.biz.entity;

import java.util.List;

/**
 * Created by foodoon on 2015/1/30.
 */
public class BookInfoVO {

    private long date;
    private String dateCN;

    private List<TimeSectionVO> timeSectionList;

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDateCN() {
        return dateCN;
    }

    public void setDateCN(String dateCN) {
        this.dateCN = dateCN;
    }

    public List<TimeSectionVO> getTimeSectionList() {
        return timeSectionList;
    }

    public void setTimeSectionList(List<TimeSectionVO> timeSectionList) {
        this.timeSectionList = timeSectionList;
    }
}
