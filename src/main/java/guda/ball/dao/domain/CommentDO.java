package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CommentDO {
    private Long id;

    @GenField(cn="评论内容",order=1,inSearchForm = false,canNull = false)
    private String msg;
    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Long userId;

    @GenField(cn="被评论ID（球员，球场，比赛）",order=1,inSearchForm = false,canNull = false)
    private Long commentId;
    @GenField(cn="被评论类型（球员，球场，比赛）",order=1,inSearchForm = false,canNull = false)
    private String commentType;

    private Date gmtModify;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}