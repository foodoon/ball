package guda.ball.dao;

import guda.ball.dao.domain.TeamApplyDO;
import guda.ball.dao.domain.TeamApplyDOCriteria;
import java.util.List;

public interface TeamApplyDOMapper {
    int countByExample(TeamApplyDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(TeamApplyDO record);

    int insertSelective(TeamApplyDO record);

    List<TeamApplyDO> selectByExample(TeamApplyDOCriteria example);

    TeamApplyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeamApplyDO record);

    int updateByPrimaryKey(TeamApplyDO record);
}