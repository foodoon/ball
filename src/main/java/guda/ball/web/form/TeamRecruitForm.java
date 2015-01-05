package guda.ball.web.form;


import guda.ball.dao.domain.TeamRecruitDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class TeamRecruitForm {
    @NotNull
    private Integer userId;

    @NotEmpty(message = "{不能为空}")
    private String recruitDesc;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRecruitDesc() {
        return recruitDesc;
    }

    public void setRecruitDesc(String desc) {
        this.recruitDesc = desc;
    }

    public TeamRecruitDO toDO() {
        TeamRecruitDO teamRecruitDO = new TeamRecruitDO();
        teamRecruitDO.setUserId(this.userId);
        teamRecruitDO.setRecruitDesc(this.recruitDesc);
        return teamRecruitDO;
    }

}
