package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.TeamRecruitDO;
import javax.validation.constraints.NotNull;

public class TeamRecruitForm {
                    @NotNull     private Long teamId;

                    @NotEmpty(message = "{不能为空}")
            private String recruitDesc;

                    @NotNull     private Long userId;

    public Long getTeamId() {
       return teamId;
    }

    public void setTeamId(Long teamId) {
       this.teamId = teamId;
    }
    public String getRecruitDesc() {
       return recruitDesc;
    }

    public void setRecruitDesc(String recruitDesc) {
       this.recruitDesc = recruitDesc;
    }
    public Long getUserId() {
       return userId;
    }

    public void setUserId(Long userId) {
       this.userId = userId;
    }

    public TeamRecruitDO toDO(){
       TeamRecruitDO teamRecruitDO  = new TeamRecruitDO();
            teamRecruitDO.setTeamId(this.teamId);
                teamRecruitDO.setRecruitDesc(this.recruitDesc);
                teamRecruitDO.setUserId(this.userId);
           return teamRecruitDO;
}

}
