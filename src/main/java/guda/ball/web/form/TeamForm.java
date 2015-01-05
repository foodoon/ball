package guda.ball.web.form;

import guda.ball.dao.domain.TeamDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class TeamForm {
    @NotEmpty(message = "{不能为空}")
    private String name;

    @NotEmpty(message = "{不能为空}")
    private String teamDesc;

    @NotNull
    private Integer canJoin;

    @NotNull
    private Integer userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public Integer getCanJoin() {
        return canJoin;
    }

    public void setCanJoin(Integer canJoin) {
        this.canJoin = canJoin;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TeamDO toDO() {
        TeamDO teamDO = new TeamDO();
        teamDO.setName(this.name);
        teamDO.setTeamDesc(this.teamDesc);
        teamDO.setCanJoin(this.canJoin);
        teamDO.setUserId(this.userId);
        return teamDO;
    }

}
