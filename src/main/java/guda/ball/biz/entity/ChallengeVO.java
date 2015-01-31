package guda.ball.biz.entity;

import guda.ball.dao.domain.*;

import java.util.List;

/**
 * Created by well on 2014/8/7.
 */
public class ChallengeVO extends ChallengeDO {


    private String challengeTimeCN;

    private String statusCN;

    private TeamDO challengeTeam;
    private List<TeamMemberVO> challengeMemberList;

    private CourtDO court;
    private CourtSiteDO courtSite;
    private List<CourtServiceDO> serviceList;
    private CourtApplyDO courtApply;

    private ChallengeDO challenge;

    private List<ChallengeApplyVO> challengeApplyList;

    public List<ChallengeApplyVO> getChallengeApplyList() {
        return challengeApplyList;
    }

    public void setChallengeApplyList(List<ChallengeApplyVO> challengeApplyList) {
        this.challengeApplyList = challengeApplyList;
    }

    public CourtSiteDO getCourtSite() {
        return courtSite;
    }

    public void setCourtSite(CourtSiteDO courtSite) {
        this.courtSite = courtSite;
    }

    public List<CourtServiceDO> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<CourtServiceDO> serviceList) {
        this.serviceList = serviceList;
    }

    public CourtApplyDO getCourtApply() {
        return courtApply;
    }

    public void setCourtApply(CourtApplyDO courtApply) {
        this.courtApply = courtApply;
    }

    public List<TeamMemberVO> getChallengeMemberList() {
        return challengeMemberList;
    }

    public void setChallengeMemberList(List<TeamMemberVO> challengeMemberList) {
        this.challengeMemberList = challengeMemberList;
    }



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


    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    public String getChallengeTimeCN() {
        return challengeTimeCN;
    }

    public void setChallengeTimeCN(String challengeTimeCN) {
        this.challengeTimeCN = challengeTimeCN;
    }
}
