package guda.ball.dao;

import guda.ball.dao.domain.CommentDO;
import guda.ball.dao.domain.CommentDOCriteria;
import java.util.List;

public interface CommentDOMapper {
    int countByExample(CommentDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentDO record);

    int insertSelective(CommentDO record);

    List<CommentDO> selectByExample(CommentDOCriteria example);

    CommentDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentDO record);

    int updateByPrimaryKey(CommentDO record);
}