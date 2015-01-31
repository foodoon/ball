package guda.ball.biz.service.impl;

import guda.ball.biz.entity.ChallengeApplyVO;
import guda.ball.biz.entity.TeamMemberVO;
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
import guda.tools.web.util.Convert;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

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
    private TeamMemberDOMapper teamMemberDOMapper;
    @Autowired
    private TeamApplyDOMapper teamApplyDOMapper;
    @Autowired
    private CourtDOMapper courtDOMapper;
    @Autowired
    private CourtSiteDOMapper courtSiteDOMapper;
    @Autowired
    private CourtServiceDOMapper courtServiceDOMapper;
    @Autowired
    private CommentDOMapper commentDOMapper;
    @Autowired
    private ChallengeApplyDOMapper challengeApplyDOMapper;
    @Autowired
    private CourtApplyDOMapper courtApplyDOMapper;
    @Autowired
    private ChallengeMemberDOMapper challengeMemberDOMapper;
    @Autowired
    private ChallengeApplyMemberDOMapper challengeApplyMemberDOMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;


    @AppRequestMapping(apiName = "challenge.create", apiVersion = "1.0")
    public BizResult create(@AppRequestParam("sid") String sid,@AppRequestParam("userIds")String userIds,@AppRequestParam("siteNo")String siteNo,final @AppRequestParam("challengeDesc")String challengeDesc) {
        if (!StringUtils.hasText(sid) ||!StringUtils.hasText(userIds) ||!StringUtils.hasText(siteNo)) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }

        //courtid-siteid-date-starttime-endtime

        final String[] siteNoArray = siteNo.split("-");
        final long courtId = Convert.toLong(siteNoArray[0]);
        final long siteId = Convert.toLong(siteNoArray[1]);
        if(siteNoArray.length!=5){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        final UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        final List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if (org.springframework.util.CollectionUtils.isEmpty(teamDOs)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_BE_TEAMER);
        }
        if (teamDOs.size() > 1) {
            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
        }
        //校验Userids,是否都是自己的球队成员
        String[] userIdArray = userIds.split("-");
        final Long[] userIdArrayLong = new Long[userIdArray.length];
        for(int i=0,len=userIdArray.length;i<len;++i){
            userIdArrayLong[i] = Convert.toLong(userIdArray[i]);
        }
        for(Long id:userIdArrayLong) {
            TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
            teamMemberDOCriteria.createCriteria().andTeamIdEqualTo(teamDOs.get(0).getId()).andUserIdEqualTo(id);
            int i = teamMemberDOMapper.countByExample(teamMemberDOCriteria);
            if(i == 0){
                return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
            }
        }

        final Date bookingDate =DateHelper.parseDate(siteNoArray[2]);
        if(bookingDate == null){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        //同一个场地同一个时间点是否发布过约战
        CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
        courtApplyDOCriteria.createCriteria().andCourtIdEqualTo(courtId)
                .andCourtSiteIdEqualTo(siteId).andBookingDateEqualTo(bookingDate);

        List<CourtApplyDO> courtApplyDOList = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
        if (!org.springframework.util.CollectionUtils.isEmpty(courtApplyDOList)) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_PUB_DUPLICATE);
        }
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    //创建场地预约记录
                    CourtApplyDO courtApplyDO = new CourtApplyDO();
                    courtApplyDO.setGmtCreate(new Date());
                    courtApplyDO.setGmtModify(new Date());
                    courtApplyDO.setBookingDate(bookingDate);
                    courtApplyDO.setBookingTime(siteNoArray[3]+"-"+siteNoArray[4]);
                    courtApplyDO.setCourtId(courtId);
                    courtApplyDO.setCourtSiteId(siteId);
                    courtApplyDO.setStatus(1);
                    courtApplyDO.setUserId(userDO.getId());
                    courtApplyDOMapper.insert(courtApplyDO);
                    //创建约战记录
                    ChallengeDO challengeDO = new ChallengeDO();
                    challengeDO.setGmtCreate(new Date());
                    challengeDO.setGmtModify(new Date());
                    challengeDO.setRequestTeamId(teamDOs.get(0).getId());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");
                    try {
                        challengeDO.setStartTime(simpleDateFormat.parse(siteNoArray[2] + siteNoArray[3]));
                        simpleDateFormat = new SimpleDateFormat("yyyyMMddHH:mm:ss");
                        challengeDO.setEndTime(simpleDateFormat.parse(siteNoArray[2] + siteNoArray[4]));
                    }catch(Exception e){

                    }
                    challengeDO.setChallengeDesc(challengeDesc);
                    challengeDO.setStatus(ChallengeStatusEnum.FALSE.value);
                    challengeDO.setCourtApplyId(courtApplyDO.getId());
                    challengeDOMapper.insert(challengeDO);
                    Set<Long> userIdSet = new HashSet<Long>();
                    userIdSet.add(userDO.getId());
                    for(Long id:userIdArrayLong){
                        userIdSet.add(id);
                    }
                    //创建约战成员
                    for(Long id:userIdSet){
                        ChallengeMemberDO challengeMemberDO = new ChallengeMemberDO();
                        challengeMemberDO.setGmtCreate(new Date());
                        challengeMemberDO.setChallengeId(challengeDO.getId());
                        challengeMemberDO.setTeamId(teamDOs.get(0).getId());
                        challengeMemberDO.setUserId(id);
                        challengeMemberDOMapper.insert(challengeMemberDO);
                    }



                }
            });

            return BizResultHelper.newSuccess();
        } catch (Exception e) {
            log.error("create challenge error", e);
        }
        return BizResultHelper.newCommonError();
    }



//    @AppRequestMapping(apiName = "challenge.update", apiVersion = "1.0")
//    public BizResult update(@AppRequestParam("sid") String sid, ChallengeDO challengeDO) {
//        if (!StringUtils.hasText(sid) || challengeDO == null || challengeDO.getId() < 1 || challengeDO.getCourtApplyId() < 1
//                || !StringUtils.hasText(challengeDO.getChallengeDesc())) {
//            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
//        }
//        BizResult bizResult = sessionBiz.checkSession(sid);
//        if (!bizResult.success) {
//            return bizResult;
//        }
//        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
//        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
//        if (userDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
//        }
//        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
//        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
//        List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
//        if (org.springframework.util.CollectionUtils.isEmpty(teamDOs)) {
//            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_BE_TEAMER);
//        }
//        if (teamDOs.size() > 1) {
//            return BizResultHelper.newResultCode(CommonResultCode.TEAM_CREATE_ONLY_ONE);
//        }
//        //查询约战信息是否是自己发布的
//        ChallengeDO challengeDO1 = challengeDOMapper.selectByPrimaryKey(challengeDO.getId());
//        if (challengeDO1 == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
//        }
////        if (challengeDO1.getTeamId() != teamDOs.get(0).getId()) {
////            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
////        }
//
//        try {
//            int update = challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
//            return BizResultHelper.newSuccess();
//        } catch (Exception e) {
//            log.error("create challenge error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }

    @AppRequestMapping(apiName = "challenge.delete", apiVersion = "1.0")
    public BizResult delete(@AppRequestParam("sid") String sid,final @AppRequestParam("challengeId") long challengeId) {
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
        final ChallengeDO challengeDO1 = challengeDOMapper.selectByPrimaryKey(challengeId);
        if (challengeDO1 == null) {
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if (challengeDO1.getRequestTeamId().longValue() != teamDOs.get(0).getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }

        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    challengeDOMapper.deleteByPrimaryKey(challengeId);
                    //删除场地预约记录
                    courtApplyDOMapper.deleteByPrimaryKey(challengeDO1.getCourtApplyId());

                    //删除约战成员信息
                    ChallengeMemberDOCriteria challengeMemberDOCriteria = new ChallengeMemberDOCriteria();
                    challengeMemberDOCriteria.createCriteria().andChallengeIdEqualTo(challengeId);
                    List<ChallengeMemberDO> challengeMemberDOs = challengeMemberDOMapper.selectByExample(challengeMemberDOCriteria);
                    for(ChallengeMemberDO challengeMemberDO:challengeMemberDOs){
                        challengeMemberDOMapper.deleteByPrimaryKey(challengeMemberDO.getId());
                    }

                    if(challengeDO1.getApplyTeamId()!=null){
                        //删除应战记录
                        ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
                        challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeId);
                        List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
                        for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                            challengeApplyDOMapper.deleteByPrimaryKey(challengeApplyDO.getId());
                        }
                        //删除应战成员信息
                        ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                        challengeApplyMemberDOCriteria.createCriteria().andChallengeIdEqualTo(challengeId);
                        List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                        for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                            challengeApplyMemberDOMapper.deleteByPrimaryKey(challengeApplyMemberDO.getId());
                        }
                    }
                }
            });


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
        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        List<Long> teamIdList = CollectionHelper.transformList(teamMemberDOs,new Transformer<TeamMemberDO, Long>() {
            @Override
            public Long transform(TeamMemberDO object) {
                return object.getTeamId();
            }
        });


        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andStatusEqualTo(ChallengeStatusEnum.FALSE.value)
                .andStartTimeGreaterThan(new Date());
        if(teamIdList.size() > 0){
            criteria.andRequestTeamIdNotIn(teamIdList);
        }
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
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId());


            CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
            if(courtApplyDO == null){
                continue;
            }
            challengeVO.setCourtApply(courtApplyDO);
            //场地信息
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
            if(courtDO == null){
                continue;
            }
            challengeVO.setCourt(courtDO);
            CourtSiteDO courtSiteDO = courtSiteDOMapper.selectByPrimaryKey(courtApplyDO.getCourtSiteId());
            if(courtSiteDO == null){
                continue;
            }
            courtSiteDO.setOpenTemplate(null);
            challengeVO.setCourtSite(courtSiteDO);
            //服务信息
            CourtServiceDOCriteria courtServiceDOCriteria = new CourtServiceDOCriteria();
            courtServiceDOCriteria.createCriteria().andCourtIdEqualTo(courtDO.getId());
            List<CourtServiceDO> courtServiceDOs = courtServiceDOMapper.selectByExample(courtServiceDOCriteria);
            challengeVO.setServiceList(courtServiceDOs);

            challengeVO.setChallengeTeam(teamDO);
            ChallengeMemberDOCriteria challengeMemberDOCriteria = new ChallengeMemberDOCriteria();
            challengeMemberDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());
            List<ChallengeMemberDO> challengeMemberDOs = challengeMemberDOMapper.selectByExample(challengeMemberDOCriteria);
            List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeMemberDOs.size());
            for(ChallengeMemberDO challengeMemberDO:challengeMemberDOs){
                UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeMemberDO.getUserId());
                teamMemberVOList.add(new TeamMemberVO(userDO1));
            }
            challengeVO.setChallengeMemberList(teamMemberVOList);

            challengeVO.setChallengeTeam(teamDO);
            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
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
        List<Long> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Long>() {
            @Override
            public Long transform(TeamDO object) {
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
           // TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());

            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId()));
            //场地信息
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
            challengeVO.setCourt(courtDO);
            CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
            challengeVO.setCourtApply(courtApplyDO);
            CourtSiteDO courtSiteDO = courtSiteDOMapper.selectByPrimaryKey(courtApplyDO.getCourtSiteId());
            challengeVO.setCourtSite(courtSiteDO);
            //服务信息
            CourtServiceDOCriteria courtServiceDOCriteria = new CourtServiceDOCriteria();
            courtServiceDOCriteria.createCriteria().andCourtIdEqualTo(courtDO.getId());
            List<CourtServiceDO> courtServiceDOs = courtServiceDOMapper.selectByExample(courtServiceDOCriteria);
            challengeVO.setServiceList(courtServiceDOs);


            ChallengeMemberDOCriteria challengeMemberDOCriteria = new ChallengeMemberDOCriteria();
            challengeMemberDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());
            List<ChallengeMemberDO> challengeMemberDOs = challengeMemberDOMapper.selectByExample(challengeMemberDOCriteria);
            List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeMemberDOs.size());
            for(ChallengeMemberDO challengeMemberDO:challengeMemberDOs){
                UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeMemberDO.getUserId());
                teamMemberVOList.add(new TeamMemberVO(userDO1));
            }
            challengeVO.setChallengeMemberList(teamMemberVOList);
            //应战成员信息
//            ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
//            challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
//            List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
//            for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
//                UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
//                teamMemberVOList.add(new TeamMemberVO(userDO1));
//            }
//            challengeVO.setApplyMemberList(teamMemberVOList);


            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
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


        List<Long> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Long>() {
            @Override
            public Long transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Long> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Long>() {
            @Override
            public Long transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andRequestTeamIdIn(teamIdList);

        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andApplyTeamIdIn(teamIdList);
        challengeDOCriteria.or(criteria);
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

            CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
            if(courtApplyDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
            }
            challengeVO.setCourtApply(courtApplyDO);
            //场地信息
            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
            if(courtDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
            }
            challengeVO.setCourt(courtDO);

            CourtSiteDO courtSiteDO = courtSiteDOMapper.selectByPrimaryKey(courtApplyDO.getCourtSiteId());
            if(courtSiteDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_SITE_NOT_EXIST);
            }
            courtSiteDO.setOpenTemplate(null);
            challengeVO.setCourtSite(courtSiteDO);
            //服务信息
            CourtServiceDOCriteria courtServiceDOCriteria = new CourtServiceDOCriteria();
            courtServiceDOCriteria.createCriteria().andCourtIdEqualTo(courtDO.getId());
            List<CourtServiceDO> courtServiceDOs = courtServiceDOMapper.selectByExample(courtServiceDOCriteria);
            challengeVO.setServiceList(courtServiceDOs);


            //应战球队
            ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
            challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeDO.getId());
            List<ChallengeApplyDO> challengeApplyDOs = challengeApplyDOMapper.selectByExample(challengeApplyDOCriteria);
            List<ChallengeApplyVO> challengeApplyVOList = new ArrayList<ChallengeApplyVO>();
            for(ChallengeApplyDO challengeApplyDO:challengeApplyDOs){
                ChallengeApplyVO challengeApplyVO = new ChallengeApplyVO();
                challengeApplyVO.setChallengeApplyDO(challengeApplyDO);
                TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
                challengeApplyVO.setTeam(teamDO);
                //应战成员信息
                ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
                List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeApplyMemberDOs.size());
                for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                    UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
                    teamMemberVOList.add(new TeamMemberVO(userDO1));
                }
                challengeApplyVO.setMemberList(teamMemberVOList);
                challengeApplyVOList.add(challengeApplyVO);
            }
            challengeVO.setChallengeApplyList(challengeApplyVOList);

            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
            challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
            challengeVOList.add(challengeVO);
        }
        BizResult bizResult1 = new BizResult();
        bizResult1.data.put("list",challengeVOList);
        bizResult1.data.put("query",baseQuery);
        bizResult1.success = true;
        return bizResult1;
    }


    @AppRequestMapping(apiName = "challenge.apply", apiVersion = "1.0")
    public BizResult apply(@AppRequestParam("sid") String sid, final @AppRequestParam("challengeId") long challengeId,@AppRequestParam("userIds")String userIds) {
        if (!StringUtils.hasText(sid)|| challengeId < 1 || !StringUtils.hasText(userIds)){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        BizResult bizResult = sessionBiz.checkSession(sid);
        if (!bizResult.success) {
            return bizResult;
        }
        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
        final UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
        if (userDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
        }
        TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
        teamDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        final List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
             return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }
        final ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeId);
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }
        if(challengeDO.getStatus()!=BooleanEnum.FALSE.value){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_HAVE_MEET);
        }
        if(challengeDO.getStartTime().before(new Date())){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
        }
        //校验是否已经申请过
        ChallengeApplyDOCriteria challengeApplyDOCriteria = new ChallengeApplyDOCriteria();
        challengeApplyDOCriteria.createCriteria().andChallengeIdEqualTo(challengeId).andTeamIdEqualTo(teamDOs.get(0).getId());
        int i1 = challengeApplyDOMapper.countByExample(challengeApplyDOCriteria);
        if(i1>0){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_REPEAT);
        }
        //校验Userids,是否都是自己的球队成员
        final String[] userIdArray = userIds.split("-");
        final Long[] userIdArrayLong = new Long[userIdArray.length];
        for(int i=0,len=userIdArray.length;i<len;++i){
            userIdArrayLong[i] = Convert.toLong(userIdArray[i]);
        }
        for(Long id:userIdArrayLong) {
            TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
            teamMemberDOCriteria.createCriteria().andTeamIdEqualTo(teamDOs.get(0).getId()).andUserIdEqualTo(id);
            int i = teamMemberDOMapper.countByExample(teamMemberDOCriteria);
            if(i == 0){
                return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
            }
        }


        try{
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    ChallengeApplyDO challengeApplyDO = new ChallengeApplyDO();
                    challengeApplyDO.setGmtModify(new Date());
                    challengeApplyDO.setGmtCreate(new Date());
                    challengeApplyDO.setChallengeId(challengeId);
                    challengeApplyDO.setTeamId(teamDOs.get(0).getId());
                    challengeApplyDO.setAccept(0);
                    challengeApplyDOMapper.insert(challengeApplyDO);
                    Set<Long> userIdSet = new HashSet<Long>();
                    userIdSet.add(userDO.getId());
                    for(Long id:userIdArrayLong){
                        userIdSet.add(id);
                    }
                    //创建应战成员
                    for(Long id:userIdSet){
                        ChallengeApplyMemberDO challengeApplyMemberDO  = new ChallengeApplyMemberDO();
                        challengeApplyMemberDO.setGmtCreate(new Date());
                        challengeApplyMemberDO.setUserId(id);
                        challengeApplyMemberDO.setChallengeId(challengeId);
                        challengeApplyMemberDO.setChallengeApplyId(challengeApplyDO.getId());
                        challengeApplyMemberDO.setTeamId(teamDOs.get(0).getId());
                        challengeApplyMemberDOMapper.insert(challengeApplyMemberDO);
                    }


                }
            });


            return BizResultHelper.newSuccess();
        }catch(Exception e){
            log.error("",e);
        }
        return BizResultHelper.newCommonError();
    }


    @AppRequestMapping(apiName = "challenge.cancelApply", apiVersion = "1.0")
    public BizResult cancelApply(@AppRequestParam("sid") String sid,final  @AppRequestParam("challengeApplyId") long challengeApplyId) {
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
        final List<TeamDO> teamDOs = teamDOMapper.selectByExample(teamDOCriteria);
        if(CollectionUtils.isEmpty(teamDOs)){
            if (userDO == null) {
                return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
            }
        }
        ChallengeApplyDO challengeApplyDO1 = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO1 == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }
        final ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO1.getChallengeId());
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }

        ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }

        try{
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    challengeApplyDOMapper.deleteByPrimaryKey(challengeApplyId);
                    //删除约战成员
                    ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                    challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyId).andTeamIdEqualTo(teamDOs.get(0).getId());
                    List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                    for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                        challengeApplyMemberDOMapper.deleteByPrimaryKey(challengeApplyMemberDO.getId());
                    }
                    //如果约战方已经通过应战，并且是自己，那么需要更新状态
                    if(challengeDO.getStatus().intValue() == BooleanEnum.TRUE.value && challengeDO.getApplyTeamId().longValue() == teamDOs.get(0).getId().longValue()) {
                        challengeDO.setStatus(BooleanEnum.FALSE.value);
                        challengeDO.setApplyTeamId(null);
                        challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
                    }
                }
            });

            return BizResultHelper.newSuccess();
        }catch(Exception e){
           log.error("",e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "challenge.passApply", apiVersion = "1.0")
    public BizResult passApply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeApplyId") long challengeApplyId) {
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
        final ChallengeDO challengeDO = challengeDOMapper.selectByPrimaryKey(challengeApplyDO1.getChallengeId());
        if(challengeDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_NOT_EXIST);
        }

        final ChallengeApplyDO challengeApplyDO = challengeApplyDOMapper.selectByPrimaryKey(challengeApplyId);
        if(challengeApplyDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_APPLY_NOT_EXIST);
        }

        try{
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    challengeApplyDO.setAccept(BooleanEnum.TRUE.value);
                    challengeApplyDOMapper.updateByPrimaryKeySelective(challengeApplyDO);
                    challengeDO.setStatus(BooleanEnum.TRUE.value);
                    challengeDO.setApplyTeamId(challengeApplyDO.getTeamId());
                    challengeDOMapper.updateByPrimaryKeySelective(challengeDO);
                }
            });

            return BizResultHelper.newSuccess();
        }catch(Exception e){
          log.error("",e);
        }
        return BizResultHelper.newCommonError();

    }

    @AppRequestMapping(apiName = "challenge.rejectApply", apiVersion = "1.0")
    public BizResult rejectApply(@AppRequestParam("sid") String sid, @AppRequestParam("challengeApplyId") long challengeApplyId) {
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
//        if(challengeDO.getChallengeTime().before(new Date())){
//            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_TIME_EXPIRE);
//        }
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

        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        List<Long> teamIdList = CollectionHelper.transformList(teamMemberDOs,new Transformer<TeamMemberDO, Long>() {
            @Override
            public Long transform(TeamMemberDO object) {
                return object.getTeamId();
            }
        });
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andRequestTeamIdIn(teamIdList);
        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andApplyTeamIdIn(teamIdList);
        challengeDOCriteria.or(criteria);
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
                       if(DateHelper.compare(challengeDO.getStartTime())){
                           if(DateHelper.sameDay(challengeDO.getStartTime())){
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

        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        List<Long> teamIdList = CollectionHelper.transformList(teamMemberDOs,new Transformer<TeamMemberDO, Long>() {
            @Override
            public Long transform(TeamMemberDO object) {
                return object.getTeamId();
            }
        });
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andRequestTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andApplyTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        challengeDOCriteria.or(criteria);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
        //先查询我发起的 或者申请的
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
                    if(DateHelper.compare(challengeDO.getStartTime())){
                        if(DateHelper.sameDay(challengeDO.getStartTime())){
                            ongoing++;
                        }else{
                            ChallengeVO challengeVO = new ChallengeVO();
                            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
                            challengeVO.setCourt(courtDO);
                            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId()));
                            //设置应战信息
                            List<ChallengeApplyVO> challengeApplyVOList = new ArrayList<ChallengeApplyVO>();
                            ChallengeApplyVO challengeApplyVO = new ChallengeApplyVO();
                            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
                            challengeApplyVO.setTeam(teamDO);
                            challengeApplyVO.setChallengeApplyDO(challengeApplyDO);
                            //应战成员信息
                            ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                            challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
                            List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                            List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeApplyMemberDOs.size());
                            for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                                UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
                                teamMemberVOList.add(new TeamMemberVO(userDO1));
                            }
                            challengeApplyVO.setMemberList(teamMemberVOList);
                            challengeApplyVOList.add(challengeApplyVO);
                            challengeVO.setChallengeApplyList(challengeApplyVOList);


                            challengeVO.setChallenge(challengeDO);
                            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
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

        TeamMemberDOCriteria teamMemberDOCriteria = new TeamMemberDOCriteria();
        teamMemberDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<TeamMemberDO> teamMemberDOs = teamMemberDOMapper.selectByExample(teamMemberDOCriteria);
        List<Long> teamIdList = CollectionHelper.transformList(teamMemberDOs,new Transformer<TeamMemberDO, Long>() {
            @Override
            public Long transform(TeamMemberDO object) {
                return object.getTeamId();
            }
        });
        if(CollectionUtils.isEmpty(teamIdList)){
            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);
        }


        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andRequestTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andApplyTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        challengeDOCriteria.or(criteria);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);

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
                    if(DateHelper.compare(challengeDO.getStartTime())){
                        if(DateHelper.sameDay(challengeDO.getStartTime())){
                            ongoing++;
                        }else{

                            waitStart++;
                        }
                    }else{
                        over++;
                        ChallengeVO challengeVO = new ChallengeVO();
                        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
                        challengeVO.setCourt(courtDO);
                        challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId()));

                        //设置应战信息
                        List<ChallengeApplyVO> challengeApplyVOList = new ArrayList<ChallengeApplyVO>();
                        ChallengeApplyVO challengeApplyVO = new ChallengeApplyVO();
                        TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
                        challengeApplyVO.setTeam(teamDO);
                        challengeApplyVO.setChallengeApplyDO(challengeApplyDO);
                        //应战成员信息
                        ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                        challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
                        List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                        List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeApplyMemberDOs.size());
                        for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                            UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
                            teamMemberVOList.add(new TeamMemberVO(userDO1));
                        }
                        challengeApplyVO.setMemberList(teamMemberVOList);
                        challengeApplyVOList.add(challengeApplyVO);
                        challengeVO.setChallengeApplyList(challengeApplyVOList);

                        challengeVO.setChallenge(challengeDO);
                        challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
                        challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                        challengeVOList.add(challengeVO);
                    }
                }else if(challengeApplyDO.getAccept().intValue() == ChallengeStatusEnum.FINISH.value){
                    over++;
                    ChallengeVO challengeVO = new ChallengeVO();
                    CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
                    challengeVO.setCourt(courtDO);
                    challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId()));

                    //设置应战信息
                    List<ChallengeApplyVO> challengeApplyVOList = new ArrayList<ChallengeApplyVO>();
                    ChallengeApplyVO challengeApplyVO = new ChallengeApplyVO();
                    TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
                    challengeApplyVO.setTeam(teamDO);
                    challengeApplyVO.setChallengeApplyDO(challengeApplyDO);
                    //应战成员信息
                    ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                    challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
                    List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                    List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeApplyMemberDOs.size());
                    for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                        UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
                        teamMemberVOList.add(new TeamMemberVO(userDO1));
                    }
                    challengeApplyVO.setMemberList(teamMemberVOList);
                    challengeApplyVOList.add(challengeApplyVO);
                    challengeVO.setChallengeApplyList(challengeApplyVOList);

                    challengeVO.setChallenge(challengeDO);
                    challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
                    challengeVO.setStatusCN(ChallengeStatusEnum.getByValue(challengeDO.getStatus()).msg);
                    challengeVOList.add(challengeVO);
                }
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

        List<Long> teamIdList = CollectionHelper.transformList(teamDOs,new Transformer<TeamDO, Long>() {
            @Override
            public Long transform(TeamDO object) {
                return object.getId();
            }
        });

        //查找我加入的球队
        TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
        teamApplyDOCriteria.createCriteria().andStatusEqualTo(ApplyStatusEnum.PASS.value)
                .andUserIdEqualTo(userDO.getId());
        List<TeamApplyDO> teamApplyDOs = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
        List<Long> joinTeamIdList = CollectionHelper.transformList(teamApplyDOs,new Transformer<TeamApplyDO, Long>() {
            @Override
            public Long transform(TeamApplyDO object) {
                return object.getTeamId();
            }
        });
        teamIdList.addAll(joinTeamIdList);

        if(CollectionUtils.isEmpty(teamIdList)){

            return BizResultHelper.newResultCode(CommonResultCode.CHALLENGE_MUST_HAVE_TEAM);

        }

        ChallengeDOCriteria challengeDOCriteria = new ChallengeDOCriteria();
        challengeDOCriteria.createCriteria().andRequestTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        ChallengeDOCriteria.Criteria criteria = challengeDOCriteria.createCriteria().andApplyTeamIdIn(teamIdList).andStatusEqualTo(BooleanEnum.TRUE.value);
        challengeDOCriteria.or(criteria);
        List<ChallengeDO> challengeDOs = challengeDOMapper.selectByExample(challengeDOCriteria);
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
                    if(DateHelper.compare(challengeDO.getStartTime())){
                        if(DateHelper.sameDay(challengeDO.getStartTime())){
                            ongoing++;
                            ChallengeVO challengeVO = new ChallengeVO();
                            CourtDO courtDO = courtDOMapper.selectByPrimaryKey(challengeDO.getCourtApplyId());
                            challengeVO.setCourt(courtDO);
                            challengeVO.setChallengeTeam(teamDOMapper.selectByPrimaryKey(challengeDO.getRequestTeamId()));

                            //设置应战信息
                            List<ChallengeApplyVO> challengeApplyVOList = new ArrayList<ChallengeApplyVO>();
                            ChallengeApplyVO challengeApplyVO = new ChallengeApplyVO();
                            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(challengeApplyDO.getTeamId());
                            challengeApplyVO.setTeam(teamDO);
                            challengeApplyVO.setChallengeApplyDO(challengeApplyDO);
                            //应战成员信息
                            ChallengeApplyMemberDOCriteria challengeApplyMemberDOCriteria = new ChallengeApplyMemberDOCriteria();
                            challengeApplyMemberDOCriteria.createCriteria().andChallengeApplyIdEqualTo(challengeApplyDO.getId());
                            List<ChallengeApplyMemberDO> challengeApplyMemberDOs = challengeApplyMemberDOMapper.selectByExample(challengeApplyMemberDOCriteria);
                            List<TeamMemberVO> teamMemberVOList = new ArrayList<TeamMemberVO>(challengeApplyMemberDOs.size());
                            for(ChallengeApplyMemberDO challengeApplyMemberDO:challengeApplyMemberDOs){
                                UserDO userDO1 = userDOMapper.selectByPrimaryKey(challengeApplyMemberDO.getUserId());
                                teamMemberVOList.add(new TeamMemberVO(userDO1));
                            }
                            challengeApplyVO.setMemberList(teamMemberVOList);
                            challengeApplyVOList.add(challengeApplyVO);
                            challengeVO.setChallengeApplyList(challengeApplyVOList);

                            challengeVO.setChallenge(challengeDO);
                            challengeVO.setChallengeTimeCN(DateHelper.formatYMDHMCN(challengeDO.getStartTime())+"-" + DateHelper.formatHMCN(challengeDO.getEndTime()));
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

        BizResult bizResult1 = new BizResult();
        bizResult1.success=true;
        bizResult1.data.put("list",challengeVOList);
        return bizResult1;
    }

}
