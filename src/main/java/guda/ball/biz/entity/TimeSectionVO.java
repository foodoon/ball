package guda.ball.biz.entity;

/**
 * Created by foodoon on 2015/1/30.
 */
public class TimeSectionVO {

    private String siteNo;
    private long startTime;
    private long endTime;
    private String startTimeCN;
    private String endTimeCN;

    private boolean bookStatus;

    public String getSiteNo() {
        return siteNo;
    }

    public void setSiteNo(String siteNo) {
        this.siteNo = siteNo;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getStartTimeCN() {
        return startTimeCN;
    }

    public void setStartTimeCN(String startTimeCN) {
        this.startTimeCN = startTimeCN;
    }

    public String getEndTimeCN() {
        return endTimeCN;
    }

    public void setEndTimeCN(String endTimeCN) {
        this.endTimeCN = endTimeCN;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }


}
