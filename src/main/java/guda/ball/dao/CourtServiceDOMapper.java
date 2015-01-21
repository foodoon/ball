package guda.ball.dao;

import guda.ball.dao.domain.CourtServiceDO;
import guda.ball.dao.domain.CourtServiceDOCriteria;
import java.util.List;

public interface CourtServiceDOMapper {
    int countByExample(CourtServiceDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CourtServiceDO record);

    int insertSelective(CourtServiceDO record);

    List<CourtServiceDO> selectByExample(CourtServiceDOCriteria example);

    CourtServiceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourtServiceDO record);

    int updateByPrimaryKey(CourtServiceDO record);
}