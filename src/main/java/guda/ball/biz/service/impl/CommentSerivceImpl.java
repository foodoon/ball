package guda.ball.biz.service.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.entity.CommentVO;
import guda.ball.biz.service.CommentSerivce;
import guda.ball.dao.*;
import guda.ball.dao.domain.*;
import guda.ball.util.AppRequestMapping;
import guda.ball.util.AppRequestParam;
import guda.ball.util.BizResultHelper;
import guda.ball.util.CommonResultCode;
import guda.ball.util.enums.CommentTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by well on 2014/8/10.
 */
public class CommentSerivceImpl implements CommentSerivce{

    private final static Logger log = LoggerFactory.getLogger(CommentSerivceImpl.class);
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private ChallengeDOMapper challengeDOMapper;
    @Autowired
    private TeamDOMapper teamDOMapper;
    @Autowired
    private CourtDOMapper courtDOMapper;
    @Autowired
    private CommentDOMapper commentDOMapper;

    @AppRequestMapping(apiName = "comment.create", apiVersion = "1.0")
    public BizResult comment(@AppRequestParam("sid") String sid,@AppRequestParam("commentId") int commentId,@AppRequestParam("commentType")  String commentType,@AppRequestParam("msg")  String msg) {
        if (!StringUtils.hasText(sid) || commentId < 1
                || !StringUtils.hasText(commentType)
                || !StringUtils.hasText(msg)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        if(CommentTypeEnum.CHALLENGE.value.equals(commentType)){
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(commentId);
            if(challengeDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
            }
        }else if(CommentTypeEnum.COURT.value.equals(commentType)){
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(commentId);
            if(courtDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
            }
            if(courtDO.getUserId() == userDO.getId()){
                return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
            }
        }else if(CommentTypeEnum.TEAM.value.equals(commentType)){
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(commentId);
            if(teamDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.TEAM_NOT_EXIST);
            }
        }else if(CommentTypeEnum.USER.value.equals(commentType)){
            UserDO userDO2 = userDOMapper.selectByPrimaryKey(commentId);
            if(userDO2 == null){
                return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
            }
            if(userDO2.getId() == userDO.getId()){
                return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
            }
        }else{
            return BizResultHelper.newResultCode(CommonResultCode.COMENT_TYPE_ERROR);
        }
        CommentDO commentDO = new CommentDO();
        commentDO.setCommentType(commentType);
        commentDO.setIsDeleted(0);
        commentDO.setGmtCreate(new Date());
        commentDO.setGmtModify(new Date());
        commentDO.setCommentId(commentId);
        commentDO.setMsg(msg);
        commentDO.setUserId(userDO.getId());
        try {
            commentDOMapper.insert(commentDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("create commont error",e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "comment.queryCommentList", apiVersion = "1.0")
    public BizResult queryCommentList(@AppRequestParam("sid") String sid,@AppRequestParam("commentId") int commentId, @AppRequestParam("commentType") String commentType,@AppRequestParam("pageNo")  int pageNo,@AppRequestParam("pageSize")  int pageSize) {
        if (!StringUtils.hasText(sid)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        CommentDOCriteria commentDOCriteria = new CommentDOCriteria();
        commentDOCriteria.createCriteria().andCommentIdEqualTo(commentId)
        .andCommentTypeEqualTo(commentType);
        commentDOCriteria.setPageSize(baseQuery.getPageSize());
        commentDOCriteria.setStartRow(baseQuery.getStartRow());
        List<CommentDO> commentDOs = commentDOMapper.selectByExample(commentDOCriteria);
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        for(CommentDO commentDO:commentDOs){
            commentVOList.add(new CommentVO(commentDO));
        }
        int i = commentDOMapper.countByExample(commentDOCriteria);
        baseQuery.setTotalCount(i);
        BizResult bizResult1 = new BizResult();
        bizResult1.success = true;
        bizResult1.data.put("query",baseQuery);
        bizResult1.data.put("commentList",commentVOList);

        return bizResult1;
    }
}
