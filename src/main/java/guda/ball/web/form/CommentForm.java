package guda.ball.web.form;

import guda.ball.dao.domain.CommentDO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CommentForm {
    @NotNull
    private Integer challengeId;

    @NotEmpty(message = "{不能为空}")
    private String msg;

    @NotNull
    private Integer userId;

    @NotNull
    private Integer commentId;

    @NotEmpty(message = "{不能为空}")
    private String commentType;

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        this.commentType = commentType;
    }

    public CommentDO toDO() {
        CommentDO commentDO = new CommentDO();
        commentDO.setMsg(this.msg);
        commentDO.setUserId(this.userId);
        commentDO.setCommentId(this.commentId);
        commentDO.setCommentType(this.commentType);
        return commentDO;
    }

}
