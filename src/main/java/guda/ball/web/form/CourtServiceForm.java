package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.CourtServiceDO;
import javax.validation.constraints.NotNull;

public class CourtServiceForm {
                    @NotEmpty(message = "{不能为空}")
            private String serviceName;

                    @NotNull     private Long courtId;

    public String getServiceName() {
       return serviceName;
    }

    public void setServiceName(String serviceName) {
       this.serviceName = serviceName;
    }
    public Long getCourtId() {
       return courtId;
    }

    public void setCourtId(Long courtId) {
       this.courtId = courtId;
    }

    public CourtServiceDO toDO(){
       CourtServiceDO courtServiceDO  = new CourtServiceDO();
            courtServiceDO.setServiceName(this.serviceName);
                courtServiceDO.setCourtId(this.courtId);
           return courtServiceDO;
}

}
