package guda.ball.dao;

import guda.ball.dao.domain.ChallengeApplyDO;
import guda.ball.dao.domain.ChallengeApplyDOCriteria;

import java.util.List;

public interface ChallengeApplyDOMapper {
    int countByExample(ChallengeApplyDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChallengeApplyDO record);

    int insertSelective(ChallengeApplyDO record);

    List<ChallengeApplyDO> selectByExample(ChallengeApplyDOCriteria example);

    ChallengeApplyDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChallengeApplyDO record);

    int updateByPrimaryKey(ChallengeApplyDO record);
}