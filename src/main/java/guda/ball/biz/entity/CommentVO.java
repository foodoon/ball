package guda.ball.biz.entity;

import guda.ball.dao.domain.CommentDO;
import guda.ball.util.DateHelper;

import java.text.SimpleDateFormat;

/**
 * Created by well on 2014/8/10.
 */
public class CommentVO extends CommentDO {

    private String gmtCreateCN;
    public CommentVO(){

    }

    public CommentVO(CommentDO commentDO){
        this.setUserId(commentDO.getUserId());
        this.setMsg(commentDO.getMsg());
        this.setCommentId(commentDO.getCommentId());
        this.setId(commentDO.getId());
        this.setCommentType(commentDO.getCommentType());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateHelper.defaultPattern);
        this.setGmtCreateCN(simpleDateFormat.format(commentDO.getGmtCreate()));

    }

    public String getGmtCreateCN() {
        return gmtCreateCN;
    }

    public void setGmtCreateCN(String gmtCreateCN) {
        this.gmtCreateCN = gmtCreateCN;
    }
}
