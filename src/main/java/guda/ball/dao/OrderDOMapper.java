package guda.ball.dao;

import guda.ball.dao.domain.OrderDO;
import guda.ball.dao.domain.OrderDOCriteria;
import java.util.List;

public interface OrderDOMapper {
    int countByExample(OrderDOCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    List<OrderDO> selectByExample(OrderDOCriteria example);

    OrderDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);
}