package guda.ball.dao;

import guda.ball.dao.domain.TeamMemberDO;
import guda.ball.dao.domain.TeamMemberDOCriteria;
import java.util.List;

public interface TeamMemberDOMapper {
    int countByExample(TeamMemberDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamMemberDO record);

    int insertSelective(TeamMemberDO record);

    List<TeamMemberDO> selectByExample(TeamMemberDOCriteria example);

    TeamMemberDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeamMemberDO record);

    int updateByPrimaryKey(TeamMemberDO record);
}