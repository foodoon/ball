package guda.ball.dao;

import guda.ball.dao.domain.CourtSiteSectionDO;
import guda.ball.dao.domain.CourtSiteSectionDOCriteria;
import java.util.List;

public interface CourtSiteSectionDOMapper {
    int countByExample(CourtSiteSectionDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CourtSiteSectionDO record);

    int insertSelective(CourtSiteSectionDO record);

    List<CourtSiteSectionDO> selectByExample(CourtSiteSectionDOCriteria example);

    CourtSiteSectionDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourtSiteSectionDO record);

    int updateByPrimaryKey(CourtSiteSectionDO record);
}