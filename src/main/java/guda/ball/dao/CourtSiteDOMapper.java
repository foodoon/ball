package guda.ball.dao;

import guda.ball.dao.domain.CourtSiteDO;
import guda.ball.dao.domain.CourtSiteDOCriteria;
import java.util.List;

public interface CourtSiteDOMapper {
    int countByExample(CourtSiteDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CourtSiteDO record);

    int insertSelective(CourtSiteDO record);

    List<CourtSiteDO> selectByExampleWithBLOBs(CourtSiteDOCriteria example);

    List<CourtSiteDO> selectByExample(CourtSiteDOCriteria example);

    CourtSiteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourtSiteDO record);

    int updateByPrimaryKeyWithBLOBs(CourtSiteDO record);

    int updateByPrimaryKey(CourtSiteDO record);
}