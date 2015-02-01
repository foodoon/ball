package guda.ball.biz.service.impl;

import guda.ball.biz.TeamBiz;
import guda.ball.biz.entity.TeamVO;
import guda.ball.dao.*;
import guda.ball.util.*;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.entity.TeamApplyVO;
import guda.ball.biz.entity.TeamMemberVO;
import guda.ball.biz.service.TeamService;
import guda.ball.dao.domain.*;
import guda.ball.util.enums.ApplyStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
* Created by foodoon on 2014/8/2.
*/
@Service
public class TeamServiceImpl implements TeamService{

    private final static Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    @Autowired
    private TeamBiz teamBiz;
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private TeamDOMapper teamDOMapper;
    @Autowired
    private TeamApplyDOMapper teamApplyDOMapper;
    @Autowired
    private TeamMemberDOMapper teamMemberDOMapper;
    @Autowired
    private ChallengeApplyDOMapper challengeApplyDOMapper;
    @Autowired
    private ChallengeDOMapper challengeDOMapper;

    @AppRequestMapping(apiName = "team.apply", apiVersion = "1.0")
    public BizResult apply(@AppRequestParam("sid") String sid, @AppRequestParam("teamId") long teamId) {
        //检查team 是否存在，并且是否在招人
        if(teamId<1 || !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(teamId);
        if(teamDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_NOT_EXIST);
        }
        if(teamDO.getCanJoin() != 1){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_NOT_OPEN);
        }
        //自己不能加入自己的球队
        if(sessionDO.getUserId() == teamDO.getUserId()){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_ALLOW_NOT_ALLOW);
        }
        //判断是否已经申请过
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andUserIdEqualTo(sessionDO.getUserId()).andTeamIdEqualTo(teamId);
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        if(teamApplyDOs.size()>0){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_ALLOW_REPEAT);
        }
        TeamApplyDO teamApplyDO = new TeamApplyDO();
        teamApplyDO.setGmtCreate(new Date());
        teamApplyDO.setGmtModify(new Date());
        teamApplyDO.setUserId(sessionDO.getUserId());
        teamApplyDO.setTeamId(teamDO.getId());
        teamApplyDO.setStatus(ApplyStatusEnum.INIT.value);
        try {
            int insert = teamApplyDOMapper.insert(teamApplyDO);
            if(insert == 1){
                return BizResultHelper.newSuccess();
            }
        }catch(Exception e){
            log.error("create apply error",e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "team.cancelApply", apiVersion = "1.0")
    public BizResult cancelApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") long applyId) {
        if(applyId<1 || !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamApplyDO teamApplyDO = teamApplyDOMapper.selectByPrimaryKey(applyId);
        if(teamApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_NOT_EXIST);
        }
        if(teamApplyDO.getStatus() != ApplyStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_STATUS_ERROR);
        }

        try {
            int count = teamApplyDOMapper.deleteByPrimaryKey(applyId);
            if(count == 1){
                return BizResultHelper.newSuccess();
            }
        }catch(Exception e){
            log.error("delete apply error",e);
        }
        return BizResultHelper.newCommonError();

    }

    @AppRequestMapping(apiName = "team.create", apiVersion = "1.0")
    public BizResult create(@AppRequestParam("sid") String sid, TeamDO teamDO) {
        if(!StringUtils.hasText(sid) || teamDO == null || !StringUtils.hasText(teamDO.getName())){
              return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if(userDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        //一个人只能创建一个球队
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        TeamDOCriteria.Criteria criteria = teamDOCriteria.createCriteria();
        criteria.andUserIdEqualTo(userDO.getId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(teamDOs.size()>0){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
        }
        teamDO.setGmtCreate(new Date());
        teamDO.setGmtModify(new Date());
        teamDO.setCanJoin(1);
        teamDO.setUserId(userDO.getId());
        try {
            teamDOMapper.insert(teamDO);

            TeamMemberDO teamMemberDO = new TeamMemberDO();
            teamMemberDO.setGmtCreate(new Date());
            teamMemberDO.setGmtModify(new Date());
            teamMemberDO.setTeamId(teamDO.getId());
            teamMemberDO.setUserId(userDO.getId());
            teamMemberDO.setCreator(1);
            teamMemberDOMapper.insert(teamMemberDO);
            BizResult bizResult1 = new BizResult();
            bizResult1.data.put("id",teamDO.getId());
            bizResult1.success = true;
            return bizResult1;
        }catch(Exception e){
            log.error("create team error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "team.update", apiVersion = "1.0")
    public BizResult update(@AppRequestParam("sid") String sid,TeamDO teamDO) {

        if(!StringUtils.hasText(sid) || teamDO == null || !StringUtils.hasText(teamDO.getName())){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if(userDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        //校验是否是自己的球队
        TeamDO teamDO1 = teamDOMapper.selectByPrimaryKey(teamDO.getId());
        if(teamDO1.getUserId() != userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        teamDO.setGmtModify(new Date());
        try {
            return teamBiz.update(teamDO);
        }catch(Exception e){
            log.error("create team error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "team.delete", apiVersion = "1.0")
    public BizResult delete(@AppRequestParam("sid") String sid,@AppRequestParam("id") long id) {

        if(!StringUtils.hasText(sid) || id <1){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if(userDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        //校验是否是自己的球队
        TeamDO teamDO1 = teamDOMapper.selectByPrimaryKey(id);
        if(teamDO1.getUserId() != userDO.getId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        try {
            return teamBiz.delete(id);
        }catch(Exception e){
            log.error("create team error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "team.queryMyTeamInfo", apiVersion = "1.0")
    @Override
    public BizResult queryMyTeamInfo(@AppRequestParam("sid") String sid) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if(userDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        TeamDOCriteria.Criteria criteria = teamDOCriteria.createCriteria();
        criteria.andUserIdEqualTo(userDO.getId());

        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        BizResult bizResult1 = new BizResult();
        List<TeamVO> teamVOList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, TeamVO>() {
            @Override
            public TeamVO transform(TeamDO object) {
                return new TeamVO(object);
            }
        });
        bizResult1.data.put("list",teamVOList);
        bizResult1.success = true;
        return bizResult1;
    }

    @AppRequestMapping(apiName = "team.queryMyTeamList", apiVersion = "1.0")
    @Override
    public BizResult queryMyTeamList(@AppRequestParam("sid") String sid) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if(userDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        TeamDOCriteria.Criteria criteria = teamDOCriteria.createCriteria();
        criteria.andUserIdEqualTo(userDO.getId());

        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);


        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        TeamMemberDOCriteria.Criteria criteria1 = teamMemberDOCriteria.createCriteria();
        criteria1.andUserIdEqualTo(userDO.getId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        List<Long> teamIdList = CollectionHelper.transformList(teamMemberDOs,new Transformer<TeamMemberDO, Long>() {
            @Override
            public Long transform(TeamMemberDO object) {
                return object.getId();
            }
        });
        BizResult bizResult1 = new BizResult();
        if(teamIdList.size()>0) {
            TeamDOCriteria teamDOCriteria2 = new TeamDOCriteria();
            TeamDOCriteria.Criteria criteria2 = teamDOCriteria2.createCriteria();
            criteria2.andIdIn(teamIdList);
            List<TeamDO> teamDOList = teamDOMapper.selectByExample(teamDOCriteria2);
            teamDOs.addAll(teamDOList);
        }

        List<TeamVO> teamVOList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, TeamVO>() {
            @Override
            public TeamVO transform(TeamDO object) {
                return new TeamVO(object);
            }
        });
        bizResult1.data.put("list",teamVOList);
        bizResult1.success = true;
        return bizResult1;
    }


    @AppRequestMapping(apiName = "team.passApply", apiVersion = "1.0")
    public BizResult passApply(@AppRequestParam("sid") String sid,@AppRequestParam("applyId")  long applyId) {
        if(applyId<1 || !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamApplyDO teamApplyDO = teamApplyDOMapper.selectByPrimaryKey(applyId);
        if(teamApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_NOT_EXIST);
        }
        if(teamApplyDO.getStatus() != ApplyStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_STATUS_ERROR);
        }
        //判断球队是否是自己的
        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(teamApplyDO.getTeamId());
        if(teamDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_NOT_EXIST);
        }
        if(sessionDO.getUserId()!=teamDO.getUserId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        teamApplyDO.setStatus(ApplyStatusEnum.PASS.value);
        try {
            teamApplyDOMapper.updateByPrimaryKeySelective(teamApplyDO);
            TeamMemberDO teamMemberDO = new TeamMemberDO();
            teamMemberDO.setUserId(teamApplyDO.getUserId());
            teamMemberDO.setTeamId(teamApplyDO.getTeamId());
            teamMemberDO.setCreator(0);
            teamMemberDO.setGmtCreate(new Date());
            teamMemberDO.setGmtModify(new Date());
            teamMemberDOMapper.insert(teamMemberDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("pass apply error",e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "team.rejectApply", apiVersion = "1.0")
    public BizResult rejectApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") long applyId) {
        if(applyId<1 || !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamApplyDO teamApplyDO = teamApplyDOMapper.selectByPrimaryKey(applyId);
        if(teamApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_NOT_EXIST);
        }
        if(teamApplyDO.getStatus() != ApplyStatusEnum.INIT.value){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_APPLY_STATUS_ERROR);
        }
        //判断球队是否是自己的
        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(teamApplyDO.getTeamId());
        if(teamDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_NOT_EXIST);
        }
        if(sessionDO.getUserId()!=teamDO.getUserId()){
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        teamApplyDO.setStatus(ApplyStatusEnum.REJECT.value);
        try {
            teamApplyDOMapper.updateByPrimaryKeySelective(teamApplyDO);
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("reject apply error",e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "team.queryApplyListForReview", apiVersion = "1.0")
    public BizResult queryApplyListForReview(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(sessionDO.getUserId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(teamDOs.size() == 0){
            BizResult bizResult1 = new BizResult();
            bizResult1.data.put("list", Collections.emptyList());
            bizResult1.data.put("query",baseQuery);
            return bizResult1;
        }
        List<Long> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Long>() {
            @Override
            public Long transform(TeamDO object) {
                return object.getId();
            }
        });
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        TeamApplyDOCriteria.Criteria criteria = teamApplyDOCriteria.createCriteria();
        criteria.andTeamIdIn(teamIdList);

        teamApplyDOCriteria.setStartRow(baseQuery.getStartRow());
        teamApplyDOCriteria.setPageSize(baseQuery.getPageSize());
        teamApplyDOCriteria.setOrderByClause(" gmt_create desc");
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        int count = teamApplyDOMapper.countByExample(teamApplyDOCriteria);
        baseQuery.setTotalCount(count);
        List<TeamApplyVO> teamApplyVOList = new ArrayList<TeamApplyVO>();
        for(TeamApplyDO applyDO :teamApplyDOs){
            TeamApplyVO teamApplyVO = new TeamApplyVO(applyDO);
            UserDO userDO = userDOMapper.selectByPrimaryKey(applyDO.getUserId());
            teamApplyVO.setApplyUser(userDO);
            teamApplyVO.setApplyUserName(userDO.getUserName());
            teamApplyVO.setApplyRealName(userDO.getRealName());
            teamApplyVO.setStatusCN(ApplyStatusEnum.getByValue(applyDO.getStatus()).msg);
            teamApplyVO.setTeam(teamDOMapper.selectByPrimaryKey(applyDO.getTeamId()));
            teamApplyVOList.add(teamApplyVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("list",teamApplyVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }

    @AppRequestMapping(apiName = "team.queryMyApplyList", apiVersion = "1.0")
    public BizResult queryMyApplyList(@AppRequestParam("sid") String sid ,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");

        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        TeamApplyDOCriteria.Criteria criteria = teamApplyDOCriteria.createCriteria();
        criteria.andUserIdEqualTo(sessionDO.getUserId());

        teamApplyDOCriteria.setStartRow(baseQuery.getStartRow());
        teamApplyDOCriteria.setPageSize(baseQuery.getPageSize());
        teamApplyDOCriteria.setOrderByClause(" gmt_create desc");
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        int count = teamApplyDOMapper.countByExample(teamApplyDOCriteria);
        baseQuery.setTotalCount(count);
        List<TeamApplyVO> teamApplyVOList = new ArrayList<TeamApplyVO>();
        for(TeamApplyDO applyDO :teamApplyDOs){
            TeamApplyVO teamApplyVO = new TeamApplyVO(applyDO);
            UserDO userDO = userDOMapper.selectByPrimaryKey(applyDO.getUserId());
            teamApplyVO.setApplyUserName(userDO.getUserName());
            teamApplyVO.setApplyRealName(userDO.getRealName());
            teamApplyVO.setStatusCN(ApplyStatusEnum.getByValue(applyDO.getStatus()).msg);
            teamApplyVO.setTeam(teamDOMapper.selectByPrimaryKey(applyDO.getTeamId()));
            teamApplyVO.setApplyUser(userDO);
            teamApplyVOList.add(teamApplyVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("list",teamApplyVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }

    @AppRequestMapping(apiName = "team.removeMember", apiVersion = "1.0")
    public BizResult removeMember(@AppRequestParam("sid") String sid,@AppRequestParam("removeUserId")  long removeUserId) {
        if( !StringUtils.hasText(sid) || removeUserId <1){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(sessionDO.getUserId());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(teamDOs.size() == 0){
            BizResult bizResult1 = new BizResult();
            bizResult1.data.put("list", Collections.emptyList());
            return bizResult1;
        }
        //判断是否是自己球队的成员
        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andTeamIdEqualTo(teamDOs.get(0).getId())
        .andUserIdEqualTo(sessionDO.getUserId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        if(teamMemberDOs.size()!=1){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        try {
             teamMemberDOMapper.deleteByPrimaryKey(teamMemberDOs.get(0).getId());
            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("delete member error");
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "team.queryMemberList", apiVersion = "1.0")
    public BizResult queryMemberList(@AppRequestParam("sid") String sid ,@AppRequestParam("teamId") long teamId ,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
//        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");


        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(teamId);

        if(teamDO == null){
            BizResult bizResult1 = new BizResult();
            bizResult1.data.put("teamDO",teamDO);
            bizResult1.data.put("list", Collections.emptyList());
            bizResult1.data.put("query",baseQuery);
            bizResult1.success = true;
            return bizResult1;
        }
        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andTeamIdEqualTo(teamId);
        teamMemberDOCriteria.setStartRow(baseQuery.getStartRow());
        teamMemberDOCriteria.setPageSize(baseQuery.getPageSize());

        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        int i = teamMemberDOMapper.countByExample(teamMemberDOCriteria);
        baseQuery.setTotalCount(i);
        List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(teamMemberDOs.size());
        for(TeamMemberDO teamMemberDO:teamMemberDOs){

            UserDO userDO = userDOMapper.selectByPrimaryKey(teamMemberDO.getUserId());
            if(userDO!=null){
                TeamMemberVO teamMemberVO = new TeamMemberVO();
                teamMemberVO.setMemberUserId(userDO.getId());
                teamMemberVO.setMemberUserName(userDO.getUserName());
                teamMemberVO.setMemberUserRealName(userDO.getRealName());
                teamMemberVOList.add(teamMemberVO);
            }

        }
        bizResult.data.put("teamDO",teamDO);
        bizResult.data.put("memberList",teamMemberVOList);
        bizResult.data.put("query",baseQuery);
        bizResult.success = true;
        return bizResult;
    }
    @AppRequestMapping(apiName = "team.queryTeamList", apiVersion = "1.0")
    public BizResult queryTeamList(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo") int pageNo,@AppRequestParam("pageSize") int pageSize) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageNo);
        baseQuery.setPageSize(pageSize);
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
       // teamDOCriteria.createCriteria().andUserIdNotEqualTo(sessionDO.getUserId());
        teamDOCriteria.setStartRow(baseQuery.getStartRow());
        teamDOCriteria.setPageSize(baseQuery.getPageSize());
        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(teamDOs.size() == 0){
            BizResult bizResult1 = new BizResult();
            bizResult1.data.put("list", Collections.emptyList());
            bizResult1.data.put("query",baseQuery);
            bizResult1.success=true;
            return bizResult1;
        }
        int count = teamDOMapper.countByExample(teamDOCriteria);
        baseQuery.setTotalCount(count);
        bizResult.data.put("teamList",teamDOs);
        bizResult.data.put("query",baseQuery);
        bizResult.success=true;
        return bizResult;
    }
    @AppRequestMapping(apiName = "team.queryTeamInfo", apiVersion = "1.0")
    @Override
    public BizResult queryTeamInfo(@AppRequestParam("sid") String sid,@AppRequestParam("teamId")  long teamId) {
        if( !StringUtils.hasText(sid)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if(!bizResult.success) {
            return bizResult;
        }
       // SessionDO sessionDO = (SessionDO)bizResult.data.get("sessionDO");
        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(teamId);
        BizResult bizResult1 = new BizResult();
        TeamVO teamVO = new TeamVO(teamDO);
        //查询队长
        UserDO userDO = userDOMapper.selectByPrimaryKey(teamDO.getUserId());
        teamVO.setTeamLeader(userDO);
        //查询球员数量
        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andTeamIdEqualTo(teamDO.getId());
        int i = teamMemberDOMapper.countByExample(teamMemberDOCriteria);
        teamVO.setMemberCount(i);
        //查询应战次数
        ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andTeamIdEqualTo(teamDO.getId());
        teamVO.setApplyBallCount(challengeApplyDOMapper.countByExample(challengeApplyDOCriteria));
        //查询约战次数
        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
       // challengeDOCriteria.createCriteria().andTeamIdEqualTo(teamDO.getId());
        teamVO.setBallCount(challengeDOMapper.countByExample(challengeDOCriteria));
        bizResult1.data.put("teamInfo", teamVO);
        bizResult1.success=true;
        return bizResult1;
    }

}
