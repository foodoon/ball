package guda.ball.dao;

import guda.ball.dao.domain.CourtDO;
import guda.ball.dao.domain.CourtDOCriteria;

import java.util.List;

public interface CourtDOMapper {
    int countByExample(CourtDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourtDO record);

    int insertSelective(CourtDO record);

    List<CourtDO> selectByExample(CourtDOCriteria example);

    CourtDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourtDO record);

    int updateByPrimaryKey(CourtDO record);
}