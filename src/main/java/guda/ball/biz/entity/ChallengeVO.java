package guda.ball.biz.entity;

import guda.ball.dao.domain.ChallengeDO;
import guda.ball.dao.domain.CourtDO;
import guda.ball.dao.domain.TeamDO;

/**
 * Created by well on 2014/8/7.
 */
public class ChallengeVO extends ChallengeDO{


    private String courtName;

    private String courtAddr;

    private String teamName;

    private String challengeTimeCN;

    private String statusCN;

    private TeamDO challengeTeam;
    private TeamDO applyTeam;
    private CourtDO court;
    private ChallengeDO challenge;

    public ChallengeDO getChallenge() {
        return challenge;
    }

    public void setChallenge(ChallengeDO challenge) {
        this.challenge = challenge;
    }

    public CourtDO getCourt() {
        return court;
    }

    public void setCourt(CourtDO court) {
        this.court = court;
    }

    public TeamDO getChallengeTeam() {
        return challengeTeam;
    }

    public void setChallengeTeam(TeamDO challengeTeam) {
        this.challengeTeam = challengeTeam;
    }

    public TeamDO getApplyTeam() {
        return applyTeam;
    }

    public void setApplyTeam(TeamDO applyTeam) {
        this.applyTeam = applyTeam;
    }

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
