package guda.ball.biz.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.CommentBiz;
import guda.ball.dao.CommentDOMapper;
import guda.ball.dao.domain.CommentDO;
import guda.ball.dao.domain.CommentDOCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentBizImpl implements CommentBiz{

    private final static Logger logger = LoggerFactory.getLogger(CommentBizImpl.class);

    @Autowired
    private CommentDOMapper commentDOMapper;

    public BizResult detail(int id) {
        BizResult bizResult = new BizResult();
        try{
            CommentDO commentDO = commentDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("commentDO", commentDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Comment error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            CommentDOCriteria commentDOCriteria = new CommentDOCriteria();
            commentDOCriteria.setStartRow(baseQuery.getStartRow());
            commentDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = commentDOMapper.countByExample(commentDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<CommentDO> commentDOList = commentDOMapper.selectByExample(commentDOCriteria);
            bizResult.data.put("commentDOList", commentDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Comment list error", e);
        }
            return bizResult;
     }

    public BizResult delete(int id) {
        BizResult bizResult = new BizResult();
        try {
            commentDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete comment error", e);
        }
        return bizResult;
    }

    public BizResult create(CommentDO commentDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = commentDOMapper.insert(commentDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Comment error", e);
        }
        return bizResult;
    }

    public BizResult update(CommentDO commentDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = commentDOMapper.updateByPrimaryKeySelective(commentDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Comment error", e);
        }
        return bizResult;
    }

    }
