package guda.ball.biz.service.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.entity.ChallengeVO;
import guda.ball.biz.service.ChallengeService;
import guda.ball.dao.*;
import guda.ball.dao.domain.*;
import guda.ball.util.*;
import guda.ball.util.enums.ApplyStatusEnum;
import guda.ball.util.enums.BooleanEnum;
import guda.ball.util.enums.ChallengeStatusEnum;
import guda.ball.util.enums.CommentTypeEnum;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by foodoon on 2014/8/5.
 */
@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final static Logger log = LoggerFactory.getLogger(ChallengeServiceImpl.class);
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private ChallengeDOMapper challengeDOMapper;
    @Autowired
    private TeamDOMapper teamDOMapper;
    @Autowired
    private TeamApplyDOMapper teamApplyDOMapper;
    @Autowired
    private CourtDOMapper courtDOMapper;
    @Autowired
    private CommentDOMapper commentDOMapper;
    @Autowired
    private ChallengeApplyDOMapper challengeApplyDOMapper;



    @AppRequestMapping(apiName = "challenge.create", apiVersion = "1.0")
    public BizResult create(@AppRequestParam("sid") String sid, ChallengeDO challengeDO) {
        if (!StringUtils.hasText(sid) || challengeDO == null || challengeDO.getCourtId() < 1
                || !StringUtils.hasText(challengeDO.getChallengeDesc())
                || challengeDO.getChallengeTime() == null) {
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if (org.springframework.util.CollectionUtils.isEmpty(teamDOs)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_BE_TEAMER);
        }
        if (teamDOs.size() > 1) {
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
        }
        //同一个场地同一个时间点是否发布过约战
        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andCourtIdEqualTo(challengeDO.getCourtId())
                .andChallengeTimeBetween(DateHelper.getStartTime(challengeDO.getChallengeTime()), DateHelper.getEndTime(challengeDO.getChallengeTime()));
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        if (!org.springframework.util.CollectionUtils.isEmpty(challengeDOs)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_PUB_DUPLICATE);
        }
        challengeDO.setGmtCreate(new Date());
        challengeDO.setGmtModify(new Date());
        challengeDO.setIsDeleted(0);
        challengeDO.setStatus(ChallengeStatusEnum.FALSE.value);
        challengeDO.setTeamId(teamDOs.get(0).getId());
        try {
            challengeDOMapper.insert(challengeDO);
            return BizResultHelper.newSuccess();
        } catch (Exception e) {
            log.error("create challenge error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.update", apiVersion = "1.0")
    public BizResult update(@AppRequestParam("sid") String sid, ChallengeDO challengeDO) {
        if (!StringUtils.hasText(sid) || challengeDO == null || challengeDO.getId() < 1 || challengeDO.getCourtId() < 1
                || !StringUtils.hasText(challengeDO.getChallengeDesc())
                || challengeDO.getChallengeTime() == null) {
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if (org.springframework.util.CollectionUtils.isEmpty(teamDOs)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_BE_TEAMER);
        }
        if (teamDOs.size() > 1) {
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
        }
        //查询约战信息是否是自己发布的
        ChallengeDO challengeDO1 = challengeDOMapper.selectByPrimaryKey(challengeDO.getId());
        if (challengeDO1 == null) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if (challengeDO1.getTeamId() != teamDOs.get(0).getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }

        try {
            int update = challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
            return BizResultHelper.newSuccess();
        } catch (Exception e) {
            log.error("create challenge error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.delete", apiVersion = "1.0")
    public BizResult delete(@AppRequestParam("sid") String sid, @AppRequestParam("challengeId") int challengeId) {
        if (!StringUtils.hasText(sid) || challengeId < 1) {
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if (org.springframework.util.CollectionUtils.isEmpty(teamDOs)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_BE_TEAMER);
        }
        if (teamDOs.size() > 1) {
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
        }
        //查询约战信息是否是自己发布的
        ChallengeDO challengeDO1 = challengeDOMapper.selectByPrimaryKey(challengeId);
        if (challengeDO1 == null) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if (challengeDO1.getTeamId() != teamDOs.get(0).getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }

        try {
            challengeDOMapper.deleteByPrimaryKey(challengeId);
            return BizResultHelper.newSuccess();
        } catch (Exception e) {
            log.error("create challenge error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.queryListForApply", apiVersion = "1.0")
    public BizResult queryListForApply(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize") int pageSize) {
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
        //查找约战时间晚于当前时间的
        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andStatusEqualTo(ChallengeStatusEnum.FALSE.value)
                .andChallengeTimeGreaterThan(new Date());
        challengeDOCriteria.setStartRow(baseQuery.getStartRow());
        challengeDOCriteria.setPageSize(baseQuery.getPageSize());
        challengeDOCriteria.setOrderByClause(" gmt_modify desc");
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        int i = challengeDOMapper.countByExample(challengeDOCriteria);
        baseQuery.setTotalCount(i);
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>(challengeDOs.size());
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeVO challengeVO = new ChallengeVO();
            try {
                BeanUtils.copyProperties(challengeVO,challengeDO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId());
            challengeVO.setTeamName(teamDO.getName());
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
            challengeVO.setCourtAddr(courtDO.getAddress());
            challengeVO.setCourtName(courtDO.getName());
            challengeVO.setCourt(courtDO);
            challengeVO.setChallengeTeam(teamDO);
            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
            challengeVOList.add(challengeVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("list",challengeVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }


    @AppRequestMapping(apiName = "challenge.queryMyApplyList", apiVersion = "1.0")
    public BizResult queryMyApplyList(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo") int pageNo, @AppRequestParam("pageSize") int pageSize) {
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }
        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });
        ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        challengeApplyDOCriteria.setStartRow(baseQuery.getStartRow());
        challengeApplyDOCriteria.setPageSize(baseQuery.getPageSize());
        challengeApplyDOCriteria.setOrderByClause(" gmt_modify desc");


        List<ChallengeApplyDO> challengeApplyDOList = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
        int i = challengeApplyDOMapper.countByExample(challengeApplyDOCriteria);
        baseQuery.setTotalCount(i);
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>(challengeApplyDOList.size());
        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOList){
            ChallengeVO challengeVO = new ChallengeVO();
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO.getChallengeId());
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
            challengeVO.setTeamName(teamDO.getName());
            challengeVO.setApplyTeam(teamDO);
            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
            challengeVO.setCourt(courtDO);
            challengeVO.setCourtAddr(courtDO.getAddress());
            challengeVO.setCourtName(courtDO.getName());
            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
            challengeVOList.add(challengeVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("challengeList",challengeVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }

    @AppRequestMapping(apiName = "challenge.queryMyChallengeList", apiVersion = "1.0")
    public BizResult queryMyChallengeList(@AppRequestParam("sid") String sid, @AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize")  int pageSize) {
        if (!StringUtils.hasText(sid)){
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
        //我发起的约战
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);


        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Integer> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Integer>() {
            @Override
            public Integer transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        challengeDOCriteria.setStartRow(baseQuery.getStartRow());
        challengeDOCriteria.setPageSize(baseQuery.getPageSize());
        challengeDOCriteria.setOrderByClause(" gmt_modify desc");
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        int i = challengeDOMapper.countByExample(challengeDOCriteria);
        baseQuery.setTotalCount(i);
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>(challengeDOs.size());
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeVO challengeVO = new ChallengeVO();
            try {
                BeanUtils.copyProperties(challengeVO,challengeDO);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId());
            challengeVO.setTeamName(teamDO.getName());
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
            challengeVO.setCourtAddr(courtDO.getAddress());
            challengeVO.setCourtName(courtDO.getName());
            challengeVO.setCourt(courtDO);
            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
            challengeVOList.add(challengeVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("list",challengeVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }

    @AppRequestMapping(apiName = "challenge.comment", apiVersion = "1.0")
    public BizResult comment(@AppRequestParam("sid") String sid, @AppRequestParam("challengeId") int challengeId,@AppRequestParam("msg") String msg) {
        if (!StringUtils.hasText(sid)||!StringUtils.hasText(msg)|| challengeId < 1){
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
        ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeId);
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getStatus()!=BooleanEnum.TRUE.value){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_END);
        }
        CommentDO commentDO = new CommentDO();
        commentDO.setMsg(msg);
        commentDO.setGmtCreate(new Date());
        commentDO.setGmtModify(new Date());
        commentDO.setCommentId(challengeId);
        commentDO.setCommentType(CommentTypeEnum.CHALLENGE.value);
        commentDO.setUserId(userDO.getId());
        commentDO.setIsDeleted(0);
        try {
            commentDOMapper.insert(commentDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
           log.error("create challenge comment error",e);
        }

        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "challenge.apply", apiVersion = "1.0")
    public BizResult apply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeId") int challengeId) {
        if (!StringUtils.hasText(sid)|| challengeId < 1){
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
            if (userDO == null) {
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
            }
        }
        ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeId);
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getStatus()!=BooleanEnum.FALSE.value){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_HAVE_MEET);
        }
        if(challengeDO.getChallengeTime().before(new Date())){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
        }
        ChallengeApplyDO challengeApplyDO = new ChallengeApplyDO();
        challengeApplyDO.setIsDeleted(0);
        challengeApplyDO.setGmtModify(new Date());
        challengeApplyDO.setGmtCreate(new Date());
        challengeApplyDO.setChallengeId(challengeId);
        challengeApplyDO.setTeamId(teamDOs.get(0).getId());
        challengeApplyDO.setAccept(0);
        try{
            challengeApplyDOMapper.insert(challengeApplyDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){

        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "challenge.cancelApply", apiVersion = "1.0")
    public BizResult cancelApply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeApplyId") int challengeApplyId) {
        if (!StringUtils.hasText(sid)|| challengeApplyId < 1){
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
            if (userDO == null) {
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
            }
        }
        ChallengeApplyDO challengeApplyDO1 = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }
        ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO1.getChallengeId());
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getChallengeTime().before(new Date())){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
        }
        ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }

        try{
            challengeApplyDOMapper.deleteByPrimaryKey(challengeApplyId);
            challengeDO.setStatus(BooleanEnum.FALSE.value);
            challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){

        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.passApply", apiVersion = "1.0")
    public BizResult passApply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeApplyId") int challengeApplyId) {
        if (!StringUtils.hasText(sid)|| challengeApplyId < 1){
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
            if (userDO == null) {
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
            }
        }
        ChallengeApplyDO challengeApplyDO1 = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }
        ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO1.getChallengeId());
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getChallengeTime().before(new Date())){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
        }
        ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }

        try{
            challengeApplyDO.setAccept(BooleanEnum.TRUE.value);
            challengeApplyDOMapper.updateByPrimaryKeySelective(challengeApplyDO);
            challengeDO.setStatus(BooleanEnum.TRUE.value);
            challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){

        }
        return BizResultHelper.newCommonError();

    }

    @AppRequestMapping(apiName = "challenge.rejectApply", apiVersion = "1.0")
    public BizResult rejectApply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeApplyId") int challengeApplyId) {
        if (!StringUtils.hasText(sid)|| challengeApplyId < 1){
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
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
            if (userDO == null) {
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
            }
        }
        ChallengeApplyDO challengeApplyDO1 = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }
        ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO1.getChallengeId());
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getChallengeTime().before(new Date())){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
        }
        ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }

        try{
            challengeApplyDO.setAccept(ApplyStatusEnum.REJECT.value);
            challengeApplyDOMapper.updateByPrimaryKeySelective(challengeApplyDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){

        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.queryChallengeCount", apiVersion = "1.0")
    @Override
    public BizResult queryChallengeCount(@AppRequestParam("sid") String sid) {
        if (!StringUtils.hasText(sid)){
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

        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);

        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Integer> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Integer>() {
            @Override
            public Integer transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        //先查询我发起的
        int waitStart = 0;
        int ongoing = 0;
        int over=0;
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());

            List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                if(challengeApplyDO.getAccept() == null ){
                    continue;
                }
                if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                       if(DateHelper.compare(challengeDO.getChallengeTime())){
                           if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                               ongoing++;
                           }else{
                               waitStart++;
                           }
                       }else{
                          over++;
                       }
                }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                    over++;
                }
            }
        }
        //查询我申请的
        ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
            if(challengeApplyDO.getAccept() == null ){
                continue;
            }
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO.getChallengeId());
            if(challengeDO == null){
                continue;
            }
            if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                if(DateHelper.compare(challengeDO.getChallengeTime())){
                    if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                        ongoing++;
                    }else{
                        waitStart++;
                    }
                }else{
                    over++;
                }
            }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                over++;
            }
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.success=true;
        bizResult1.data.put("over",over);
        bizResult1.data.put("ongoing",ongoing);
        bizResult1.data.put("waitStart",waitStart);
        return bizResult1;
    }

    @AppRequestMapping(apiName = "challenge.queryWaitStartChallengeList", apiVersion = "1.0")
    @Override
    public BizResult queryWaitStartChallengeList(@AppRequestParam("sid") String sid) {
        if (!StringUtils.hasText(sid)){
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

        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
        .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Integer> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Integer>() {
            @Override
            public Integer transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);
        if(CollectionUtils.isEmpty(teamIdList)){
           return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        //先查询我发起的
        int waitStart = 0;
        int ongoing = 0;
        int over=0;
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>();
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());

            List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                if(challengeApplyDO.getAccept() == null ){
                    continue;
                }
                if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                    if(DateHelper.compare(challengeDO.getChallengeTime())){
                        if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                            ongoing++;
                        }else{
                            ChallengeVO challengeVO = new ChallengeVO();
                            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                            challengeVO.setCourt(courtDO);
                            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                            challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                            challengeVO.setChallenge(challengeDO);
                            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                            challengeVOList.add(challengeVO);
                            waitStart++;
                        }
                    }else{
                        over++;
                    }
                }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                    over++;
                }
            }
        }
        //查询我申请的
        ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
            if(challengeApplyDO.getAccept() == null ){
                continue;
            }
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO.getChallengeId());
            if(challengeDO == null){
                continue;
            }
            if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                if(DateHelper.compare(challengeDO.getChallengeTime())){
                    if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                        ongoing++;
                    }else{
                        waitStart++;
                        ChallengeVO challengeVO = new ChallengeVO();
                        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                        challengeVO.setCourt(courtDO);
                        challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                        challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                        challengeVO.setChallenge(challengeDO);
                        challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                        challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                        challengeVOList.add(challengeVO);
                    }
                }else{
                    over++;
                }
            }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                over++;
            }
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.success=true;
        bizResult1.data.put("list",challengeVOList);
        return bizResult1;
    }

    @AppRequestMapping(apiName = "challenge.queryOverChallengeList", apiVersion = "1.0")
    @Override
    public BizResult queryOverChallengeList(@AppRequestParam("sid") String sid) {
        if (!StringUtils.hasText(sid)){
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

        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);

        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });
        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Integer> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Integer>() {
            @Override
            public Integer transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);

        if(CollectionUtils.isEmpty(teamIdList)){

            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);

        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        //先查询我发起的
        int waitStart = 0;
        int ongoing = 0;
        int over=0;
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>();
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());

            List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                if(challengeApplyDO.getAccept() == null ){
                    continue;
                }
                if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                    if(DateHelper.compare(challengeDO.getChallengeTime())){
                        if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                            ongoing++;
                        }else{

                            waitStart++;
                        }
                    }else{
                        over++;
                        ChallengeVO challengeVO = new ChallengeVO();
                        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                        challengeVO.setCourt(courtDO);
                        challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                        challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                        challengeVO.setChallenge(challengeDO);
                        challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                        challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                        challengeVOList.add(challengeVO);
                    }
                }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                    over++;
                    ChallengeVO challengeVO = new ChallengeVO();
                    CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                    challengeVO.setCourt(courtDO);
                    challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                    challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                    challengeVO.setChallenge(challengeDO);
                    challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                    challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                    challengeVOList.add(challengeVO);
                }
            }
        }
        //查询我申请的
        ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
            if(challengeApplyDO.getAccept() == null ){
                continue;
            }
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO.getChallengeId());
            if(challengeDO == null){
                continue;
            }
            if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                if(DateHelper.compare(challengeDO.getChallengeTime())){
                    if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                        ongoing++;
                    }else{
                        waitStart++;

                    }
                }else{
                    over++;
                    ChallengeVO challengeVO = new ChallengeVO();
                    CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                    challengeVO.setCourt(courtDO);
                    challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                    challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                    challengeVO.setChallenge(challengeDO);
                    challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                    challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                    challengeVOList.add(challengeVO);
                }
            }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                over++;
                ChallengeVO challengeVO = new ChallengeVO();
                CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                challengeVO.setCourt(courtDO);
                challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                challengeVO.setChallenge(challengeDO);
                challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                challengeVOList.add(challengeVO);
            }
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.success=true;
        bizResult1.data.put("list",challengeVOList);
        return bizResult1;
    }

    @AppRequestMapping(apiName = "challenge.queryOngoingChallengeList", apiVersion = "1.0")
    @Override
    public BizResult queryOngoingChallengeList(@AppRequestParam("sid") String sid) {
        if (!StringUtils.hasText(sid)){
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

        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);

        List<Integer> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Integer>() {
            @Override
            public Integer transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Integer> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Integer>() {
            @Override
            public Integer transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);

        if(CollectionUtils.isEmpty(teamIdList)){

            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);

        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        //先查询我发起的
        int waitStart = 0;
        int ongoing = 0;
        int over=0;
        List<ChallengeVO> challengeVOList = new ArrayList<ChallengeVO>();
        for(ChallengeDO challengeDO:challengeDOs){
            ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());

            List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                if(challengeApplyDO.getAccept() == null ){
                    continue;
                }
                if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                    if(DateHelper.compare(challengeDO.getChallengeTime())){
                        if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                            ongoing++;
                            ChallengeVO challengeVO = new ChallengeVO();
                            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                            challengeVO.setCourt(courtDO);
                            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                            challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                            challengeVO.setChallenge(challengeDO);
                            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                            challengeVOList.add(challengeVO);
                        }else{

                            waitStart++;
                        }
                    }else{
                        over++;
                    }
                }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                    over++;
                }
            }
        }
        //查询我申请的
        ChallengeApplyDOCriteria challengeApplyDOCriteria  = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdIn(teamIdList);
        List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
            if(challengeApplyDO.getAccept() == null ){
                continue;
            }
            ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO.getChallengeId());
            if(challengeDO == null){
                continue;
            }
            if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.TRUE.value){
                if(DateHelper.compare(challengeDO.getChallengeTime())){
                    if(DateHelper.sameDay(challengeDO.getChallengeTime())){
                        ongoing++;
                        ChallengeVO challengeVO = new ChallengeVO();
                        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtId());
                        challengeVO.setCourt(courtDO);
                        challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getTeamId()));
                        challengeVO.setApplyTeam(teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId()));
                        challengeVO.setChallenge(challengeDO);
                        challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMSCN(challengeDO.getChallengeTime()));
                        challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                        challengeVOList.add(challengeVO);
                    }else{
                        waitStart++;

                    }
                }else{
                    over++;
                }
            }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                over++;
            }
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.success=true;
        bizResult1.data.put("list",challengeVOList);
        return bizResult1;
    }

}
