package guda.ball.biz.entity;

import guda.ball.dao.domain.CourtApplyDO;
import guda.ball.util.DateHelper;
import guda.ball.util.enums.ApplyStatusEnum;

import java.text.SimpleDateFormat;

/**
 * Created by foodoon on 2014/8/5.
 */
public class CourtApplyVO extends CourtApplyDO{

    private String applyUserName;

    private String applyRealName;

    private String statusCN;

    private String bookingTimeCN;


    public CourtApplyVO(){

    }

    public CourtApplyVO(CourtApplyDO courtApplyDO){
        this.setStatus(courtApplyDO.getStatus());
        this.setBookingTime(courtApplyDO.getBookingTime());
        this.setGmtModify(courtApplyDO.getGmtModify());
        this.setGmtCreate(courtApplyDO.getGmtCreate());
        this.setCourtId(courtApplyDO.getCourtId());
        this.setId(courtApplyDO.getId());
        this.setUserId(courtApplyDO.getUserId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateHelper.defaultPattern);
        this.setBookingTimeCN(simpleDateFormat.format(courtApplyDO.getBookingTime()));
        this.setStatusCN(ApplyStatusEnum.getByValue(courtApplyDO.getStatus()).msg);
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyRealName() {
        return applyRealName;
    }

    public void setApplyRealName(String applyRealName) {
        this.applyRealName = applyRealName;
    }

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getBookingTimeCN() {
        return bookingTimeCN;
    }

    public void setBookingTimeCN(String bookingTimeCN) {
        this.bookingTimeCN = bookingTimeCN;
    }
}
