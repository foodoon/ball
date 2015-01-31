package guda.ball.dao;

import guda.ball.dao.domain.ChallengeMemberDO;
import guda.ball.dao.domain.ChallengeMemberDOCriteria;
import java.util.List;

public interface ChallengeMemberDOMapper {
    int countByExample(ChallengeMemberDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(ChallengeMemberDO record);

    int insertSelective(ChallengeMemberDO record);

    List<ChallengeMemberDO> selectByExample(ChallengeMemberDOCriteria example);

    ChallengeMemberDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChallengeMemberDO record);

    int updateByPrimaryKey(ChallengeMemberDO record);
}