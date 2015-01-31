package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.TeamMemberDO;
import javax.validation.constraints.NotNull;

public class TeamMemberForm {
                    @NotNull     private Integer creator;

                    @NotNull     private Long userId;

                    @NotNull     private Long teamId;

    public Integer getCreator() {
       return creator;
    }

    public void setCreator(Integer creator) {
       this.creator = creator;
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

    public TeamMemberDO toDO(){
       TeamMemberDO teamMemberDO  = new TeamMemberDO();
            teamMemberDO.setCreator(this.creator);
                teamMemberDO.setUserId(this.userId);
                teamMemberDO.setTeamId(this.teamId);
           return teamMemberDO;
}

}
