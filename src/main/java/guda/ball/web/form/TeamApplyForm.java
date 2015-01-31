package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.TeamApplyDO;
import javax.validation.constraints.NotNull;

public class TeamApplyForm {
                    @NotEmpty(message = "{不能为空}")
            private String applyMsg;

                    @NotNull     private Integer status;

                    @NotNull     private Long userId;

                    @NotNull     private Long teamId;

    public String getApplyMsg() {
       return applyMsg;
    }

    public void setApplyMsg(String applyMsg) {
       this.applyMsg = applyMsg;
    }
    public Integer getStatus() {
       return status;
    }

    public void setStatus(Integer status) {
       this.status = status;
    }
    public Long getUserId() {
       return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }
    public Long getTeamId() {
       return teamId;
    }

    public void setTeamId(Long teamId) {
       this.teamId = teamId;
    }

    public TeamApplyDO toDO(){
       TeamApplyDO teamApplyDO  = new TeamApplyDO();
            teamApplyDO.setApplyMsg(this.applyMsg);
                teamApplyDO.setStatus(this.status);
                teamApplyDO.setUserId(this.userId);
                teamApplyDO.setTeamId(this.teamId);
           return teamApplyDO;
}

}
