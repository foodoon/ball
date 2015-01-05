package guda.ball.dao;

import guda.ball.dao.domain.TeamMemberDO;
import guda.ball.dao.domain.TeamMemberDOCriteria;

import java.util.List;

public interface TeamMemberDOMapper {
    int countByExample(TeamMemberDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeamMemberDO record);

    int insertSelective(TeamMemberDO record);

    List<TeamMemberDO> selectByExample(TeamMemberDOCriteria example);

    TeamMemberDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeamMemberDO record);

    int updateByPrimaryKey(TeamMemberDO record);
}