package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.CourtApplyDO;
import javax.validation.constraints.NotNull;

public class CourtApplyForm {
                    @NotNull     private Date bookingDate;

                    @NotEmpty(message = "{不能为空}")
            private String bookingTime;

                    @NotNull     private Long courtSiteId;

                    @NotNull     private Integer status;

                    @NotNull     private Long courtId;

                    @NotNull     private Long userId;

    public Date getBookingDate() {
       return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
       this.bookingDate = bookingDate;
    }
    public String getBookingTime() {
       return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
       this.bookingTime = bookingTime;
    }
    public Long getCourtSiteId() {
       return courtSiteId;
    }

    public void setCourtSiteId(Long courtSiteId) {
       this.courtSiteId = courtSiteId;
    }
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }
    public Long getCourtId() {
       return courtId;
    }

    public void setCourtId(Long courtId) {
       this.courtId = courtId;
    }
    public Long getUserId() {
       return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }

    public CourtApplyDO toDO(){
       CourtApplyDO courtApplyDO  = new CourtApplyDO();
            courtApplyDO.setBookingDate(this.bookingDate);
                courtApplyDO.setBookingTime(this.bookingTime);
                courtApplyDO.setCourtSiteId(this.courtSiteId);
                courtApplyDO.setStatus(this.status);
                courtApplyDO.setCourtId(this.courtId);
                courtApplyDO.setUserId(this.userId);
           return courtApplyDO;
}

}
