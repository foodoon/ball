package guda.ball.dao;

import guda.ball.dao.domain.ChallengeApplyDO;
import guda.ball.dao.domain.ChallengeApplyDOCriteria;
import java.util.List;

public interface ChallengeApplyDOMapper {
    int countByExample(ChallengeApplyDOCriteria example);

    int deleteByPrimaryKey(Long id);

    long insert(ChallengeApplyDO record);

    long insertSelective(ChallengeApplyDO record);

    List<ChallengeApplyDO> selectByExample(ChallengeApplyDOCriteria example);

    ChallengeApplyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChallengeApplyDO record);

    int updateByPrimaryKey(ChallengeApplyDO record);
}