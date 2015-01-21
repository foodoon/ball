package guda.ball.dao;

import guda.ball.dao.domain.UserDO;
import guda.ball.dao.domain.UserDOCriteria;
import java.util.List;

public interface UserDOMapper {
    int countByExample(UserDOCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExample(UserDOCriteria example);

    UserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}