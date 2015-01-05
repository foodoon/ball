package guda.ball.dao;

import guda.ball.dao.domain.ChallengeDO;
import guda.ball.dao.domain.ChallengeDOCriteria;

import java.util.List;

public interface ChallengeDOMapper {
    int countByExample(ChallengeDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChallengeDO record);

    int insertSelective(ChallengeDO record);

    List<ChallengeDO> selectByExample(ChallengeDOCriteria example);

    ChallengeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChallengeDO record);

    int updateByPrimaryKey(ChallengeDO record);
}