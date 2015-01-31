package guda.ball.dao;

import guda.ball.dao.domain.GoodsDO;
import guda.ball.dao.domain.GoodsDOCriteria;
import java.util.List;

public interface GoodsDOMapper {
    int countByExample(GoodsDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    List<GoodsDO> selectByExample(GoodsDOCriteria example);

    GoodsDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);
}