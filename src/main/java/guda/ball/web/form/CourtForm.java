package guda.ball.web.form;

import guda.ball.dao.domain.CourtDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;


public class CourtForm {
    @NotEmpty(message = "{不能为空}")
    private String name;

    @NotEmpty(message = "{不能为空}")
    private String address;

    @NotEmpty(message = "{不能为空}")
    private String type;

    @NotEmpty(message = "{不能为空}")
    private String openTime;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer status;

    @NotEmpty(message = "{不能为空}")
    private String courtDesc;

    @NotEmpty(message = "{不能为空}")
    private String square;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCourtDesc() {
        return courtDesc;
    }

    public void setCourtDesc(String desc) {
        this.courtDesc = desc;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public CourtDO toDO() {
        CourtDO courtDO = new CourtDO();
        courtDO.setName(this.name);
        courtDO.setAddress(this.address);
        courtDO.setType(this.type);
        courtDO.setOpenTime(this.openTime);
        courtDO.setUserId(this.userId);
        courtDO.setStatus(this.status);
        courtDO.setCourtDesc(this.courtDesc);
        courtDO.setSquare(this.square);
        return courtDO;
    }

}
