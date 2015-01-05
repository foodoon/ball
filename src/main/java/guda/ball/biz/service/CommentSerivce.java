package guda.ball.biz.service;


import guda.tools.web.page.BizResult;

/**
 * Created by well on 2014/8/10.
 */
public interface CommentSerivce {

    BizResult comment(String sid, int commentId, String commentType, String msg);

    BizResult queryCommentList(String sid, int commentId, String commentType, int pageNo, int pageSize);
}
