package guda.ball.dao;

import guda.ball.dao.domain.TeamRecruitDO;
import guda.ball.dao.domain.TeamRecruitDOCriteria;
import java.util.List;

public interface TeamRecruitDOMapper {
    int countByExample(TeamRecruitDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamRecruitDO record);

    int insertSelective(TeamRecruitDO record);

    List<TeamRecruitDO> selectByExample(TeamRecruitDOCriteria example);

    TeamRecruitDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeamRecruitDO record);

    int updateByPrimaryKey(TeamRecruitDO record);
}