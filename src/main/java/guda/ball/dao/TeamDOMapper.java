package guda.ball.dao;

import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.TeamDOCriteria;
import java.util.List;

public interface TeamDOMapper {
    int countByExample(TeamDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeamDO record);

    int insertSelective(TeamDO record);

    List<TeamDO> selectByExample(TeamDOCriteria example);

    TeamDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamDO record);

    int updateByPrimaryKey(TeamDO record);
}