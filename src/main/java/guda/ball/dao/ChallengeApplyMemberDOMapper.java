package guda.ball.dao;

import guda.ball.dao.domain.ChallengeApplyMemberDO;
import guda.ball.dao.domain.ChallengeApplyMemberDOCriteria;
import java.util.List;

public interface ChallengeApplyMemberDOMapper {
    int countByExample(ChallengeApplyMemberDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ChallengeApplyMemberDO record);

    int insertSelective(ChallengeApplyMemberDO record);

    List<ChallengeApplyMemberDO> selectByExample(ChallengeApplyMemberDOCriteria example);

    ChallengeApplyMemberDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChallengeApplyMemberDO record);

    int updateByPrimaryKey(ChallengeApplyMemberDO record);
}