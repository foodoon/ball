package guda.ball.biz.service;


import guda.ball.dao.domain.GoodsDO;
import guda.tools.web.page.BizResult;

import java.util.Date;

/**
* Created by foodoon on 2014/7/30.
*/
public interface GoodsService {

    public BizResult create(String sid, GoodsDO goodsDO);

    public BizResult update(String sid, GoodsDO goodsDO);

    public BizResult delete(String sid, long id);

    public BizResult queryListByCourtId(long courtId, int pageNo, int pageSize);

    public BizResult queryOrderListByBuyer(String sid, int pageNo, int pageSize);

    public BizResult queryOrderListBySeller(String sid, int pageNo, int pageSize);

    public BizResult buy(String sid, long goodsId, Date deliveryTime, String leaveMsg);

    public BizResult cancelBuy(String sid, long orderId);

    public BizResult confirmBuy(String sid, long orderId);

    public BizResult confirmGoods(String sid, long orderId);
}
