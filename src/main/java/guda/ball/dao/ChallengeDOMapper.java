package guda.ball.dao;

import guda.ball.dao.domain.ChallengeDO;
import guda.ball.dao.domain.ChallengeDOCriteria;
import java.util.List;

public interface ChallengeDOMapper {
    int countByExample(ChallengeDOCriteria example);

    int deleteByPrimaryKey(Long id);

    long insert(ChallengeDO record);

    long insertSelective(ChallengeDO record);

    List<ChallengeDO> selectByExample(ChallengeDOCriteria example);

    ChallengeDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChallengeDO record);

    int updateByPrimaryKey(ChallengeDO record);
}