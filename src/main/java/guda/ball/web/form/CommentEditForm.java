package guda.ball.web.form;

import guda.ball.dao.domain.CommentDO;


public class CommentEditForm extends CommentForm{

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommentDO toDO(){
        CommentDO commentDO  =super.toDO();
        commentDO.setId(this.id);
        return commentDO;
    }

    public void initForm(CommentDO commentDO){
        if(commentDO == null){
        return ;
    }
    this.setMsg(commentDO.getMsg());
    this.setUserId(commentDO.getUserId());
    this.setCommentId(commentDO.getCommentId());
    this.setCommentType(commentDO.getCommentType());
}

}
