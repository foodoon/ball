package guda.ball.dao.domain;

import guda.gen.GenField;

import java.util.Date;

public class CommentDO {
    private Integer id;


    @GenField(cn="约战ID",order=1,inSearchForm = false,canNull = false)
    private Integer challengeId;

    @GenField(cn="评论内容",order=1,inSearchForm = false,canNull = false)
    private String msg;
    @GenField(cn="用户ID",order=1,inSearchForm = false,canNull = false)
    private Integer userId;

    @GenField(cn="被评论ID（球员，球场，比赛）",order=1,inSearchForm = false,canNull = false)
    private Integer commentId;
    @GenField(cn="被评论类型（球员，球场，比赛）",order=1,inSearchForm = false,canNull = false)
    private String commentType;

    private Integer isDeleted;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType == null ? null : commentType.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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