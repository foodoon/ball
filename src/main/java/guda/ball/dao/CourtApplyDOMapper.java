package guda.ball.dao;

import guda.ball.dao.domain.CourtApplyDO;
import guda.ball.dao.domain.CourtApplyDOCriteria;
import java.util.List;

public interface CourtApplyDOMapper {
    int countByExample(CourtApplyDOCriteria example);

    int deleteByPrimaryKey(Long id);

    long insert(CourtApplyDO record);

    long insertSelective(CourtApplyDO record);

    List<CourtApplyDO> selectByExample(CourtApplyDOCriteria example);

    CourtApplyDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CourtApplyDO record);

    int updateByPrimaryKey(CourtApplyDO record);
}