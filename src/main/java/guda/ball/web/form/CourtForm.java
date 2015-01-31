package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.CourtDO;
import javax.validation.constraints.NotNull;

public class CourtForm {
                    @NotEmpty(message = "{不能为空}")
            private String courtDesc;

                    @NotNull     private Integer status;

                    @NotEmpty(message = "{不能为空}")
            private String tel;

                    @NotEmpty(message = "{不能为空}")
            private String contactName;

                    @NotEmpty(message = "{不能为空}")
            private String address;

                    @NotEmpty(message = "{不能为空}")
            private String name;

                    @NotNull     private Long userId;

    public String getCourtDesc() {
       return courtDesc;
    }

    public void setCourtDesc(String courtDesc) {
       this.courtDesc = courtDesc;
    }
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }
    public String getTel() {
       return tel;
    }

    public void setTel(String tel) {
       this.tel = tel;
    }
    public String getContactName() {
       return contactName;
    }

    public void setContactName(String contactName) {
       this.contactName = contactName;
    }
    public String getAddress() {
       return address;
    }

    public void setAddress(String address) {
       this.address = address;
    }
    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }
    public Long getUserId() {
       return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }

    public CourtDO toDO(){
       CourtDO courtDO  = new CourtDO();
            courtDO.setCourtDesc(this.courtDesc);
                courtDO.setStatus(this.status);
                courtDO.setTel(this.tel);
                courtDO.setContactName(this.contactName);
                courtDO.setAddress(this.address);
                courtDO.setName(this.name);
                courtDO.setUserId(this.userId);
           return courtDO;
}

}
