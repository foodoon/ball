package guda.ball.web.form;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import guda.ball.dao.domain.TeamDO;
import javax.validation.constraints.NotNull;

public class TeamForm {
                    @NotEmpty(message = "{不能为空}")
            private String jerseyColor;

                    @NotEmpty(message = "{不能为空}")
            private String homeCourt;

                    @NotEmpty(message = "{不能为空}")
            private String area;

                    @NotNull     private Integer canJoin;

                    @NotEmpty(message = "{不能为空}")
            private String teamType;

                    @NotEmpty(message = "{不能为空}")
            private String teamDesc;

                    @NotEmpty(message = "{不能为空}")
            private String name;

                    @NotNull     private Long userId;

    public String getJerseyColor() {
       return jerseyColor;
    }

    public void setJerseyColor(String jerseyColor) {
       this.jerseyColor = jerseyColor;
    }
    public String getHomeCourt() {
       return homeCourt;
    }

    public void setHomeCourt(String homeCourt) {
       this.homeCourt = homeCourt;
    }
    public String getArea() {
       return area;
    }

    public void setArea(String area) {
       this.area = area;
    }
    public Integer getCanJoin() {
       return canJoin;
    }

    public void setCanJoin(Integer canJoin) {
       this.canJoin = canJoin;
    }
    public String getTeamType() {
       return teamType;
    }

    public void setTeamType(String teamType) {
       this.teamType = teamType;
    }
    public String getTeamDesc() {
       return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
       this.teamDesc = teamDesc;
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

    public TeamDO toDO(){
       TeamDO teamDO  = new TeamDO();
            teamDO.setJerseyColor(this.jerseyColor);
                teamDO.setHomeCourt(this.homeCourt);
                teamDO.setArea(this.area);
                teamDO.setCanJoin(this.canJoin);
                teamDO.setTeamType(this.teamType);
                teamDO.setTeamDesc(this.teamDesc);
                teamDO.setName(this.name);
                teamDO.setUserId(this.userId);
           return teamDO;
}

}
