package guda.ball.biz.entity;

import guda.ball.dao.domain.ChallengeApplyDO;
import guda.ball.dao.domain.TeamDO;

import java.util.List;

/**
 * Created by foodoon on 2015/1/30.
 */
public class ChallengeApplyVO {

    private ChallengeApplyDO challengeApplyDO;
    private TeamDO team;
    private List<TeamMemberVO> memberList;

    public ChallengeApplyDO getChallengeApplyDO() {
        return challengeApplyDO;
    }

    public void setChallengeApplyDO(ChallengeApplyDO challengeApplyDO) {
        this.challengeApplyDO = challengeApplyDO;
    }

    public TeamDO getTeam() {
        return team;
    }

    public void setTeam(TeamDO team) {
        this.team = team;
    }

    public List<TeamMemberVO> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<TeamMemberVO> memberList) {
        this.memberList = memberList;
    }
}
