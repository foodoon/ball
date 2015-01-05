package guda.ball.biz.entity;

import guda.ball.dao.domain.TeamApplyDO;


/**
 * Created by foodoon on 2014/7/30.
 */
public class TeamApplyVO extends TeamApplyDO {

    public TeamApplyVO() {
    }

    public TeamApplyVO(TeamApplyDO teamApplyDO) {
        this.setId(teamApplyDO.getId());
        this.setTeamId(teamApplyDO.getTeamId());
        this.setUserId(teamApplyDO.getUserId());
        this.setStatus(teamApplyDO.getStatus());
        this.setIsDeleted(teamApplyDO.getIsDeleted());
        this.setGmtModify(teamApplyDO.getGmtModify());
        this.setGmtCreate(teamApplyDO.getGmtCreate());
    }

    private String applyUserName;

    private String applyRealName;

    private String statusCN;

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getApplyRealName() {
        return applyRealName;
    }

    public void setApplyRealName(String applyRealName) {
        this.applyRealName = applyRealName;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }
}
