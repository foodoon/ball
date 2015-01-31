package guda.ball.dao;

import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.TeamDOCriteria;
import java.util.List;

public interface TeamDOMapper {
    int countByExample(TeamDOCriteria example);

    int deleteByPrimaryKey(Long id);

    long insert(TeamDO record);

    long insertSelective(TeamDO record);

    List<TeamDO> selectByExample(TeamDOCriteria example);

    TeamDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeamDO record);

    int updateByPrimaryKey(TeamDO record);
}