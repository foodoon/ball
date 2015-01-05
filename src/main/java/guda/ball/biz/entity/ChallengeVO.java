package guda.ball.biz.entity;

import guda.ball.dao.domain.ChallengeDO;

/**
 * Created by well on 2014/8/7.
 */
public class ChallengeVO extends ChallengeDO{


    private String courtName;

    private String courtAddr;

    private String teamName;

    private String challengeTimeCN;

    private String statusCN;

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getCourtAddr() {
        return courtAddr;
    }

    public void setCourtAddr(String courtAddr) {
        this.courtAddr = courtAddr;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getChallengeTimeCN() {
        return challengeTimeCN;
    }

    public void setChallengeTimeCN(String challengeTimeCN) {
        this.challengeTimeCN = challengeTimeCN;
    }
}
