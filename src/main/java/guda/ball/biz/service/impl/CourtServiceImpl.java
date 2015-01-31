package guda.ball.biz.service.impl;

import com.alibaba.fastjson.JSON;
import guda.ball.biz.CourtApplyBiz;
import guda.ball.biz.CourtBiz;
import guda.ball.biz.entity.*;
import guda.ball.dao.*;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.service.CourtService;
import guda.ball.dao.domain.*;
import guda.ball.util.*;
import guda.ball.util.enums.ApplyStatusEnum;
import guda.ball.util.enums.CourtTypeEnum;
import guda.ball.util.enums.OpenTimeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* Created by foodoon on 2014/8/3.
*/
@Service
public class CourtServiceImpl implements CourtService {

    private final static Logger log = LoggerFactory.getLogger(CourtServiceImpl.class);

    @Autowired
    private CourtBiz courtBiz;
    @Autowired
    private SessionBiz sessionBiz;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private CourtDOMapper courtDOMapper;
    @Autowired
    private CourtApplyBiz courtApplyBiz;
    @Autowired
    private CourtApplyDOMapper courtApplyDOMapper;
    @Autowired
    private CourtSiteDOMapper courtSiteDOMapper;
    @Autowired
    private CourtSiteSectionDOMapper courtSiteSectionDOMapper;


//    @AppRequestMapping(apiName = "court.create", apiVersion = "1.0")
//    public BizResult create(@AppRequestParam("sid") String sid, CourtDO courtDO) {
//        if (!StringUtils.hasText(sid) || courtDO == null || !StringUtils.hasText(courtDO.getName())
//                || !StringUtils.hasText(courtDO.getAddress())) {
//            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
//        }
//
//        BizResult bizResult = sessionBiz.checkSession(sid);
//        if (!bizResult.success) {
//            return bizResult;
//        }
//        SessionDO sessionDO = (SessionDO) bizResult.data.get("sessionDO");
//        UserDO userDO = userDOMapper.selectByPrimaryKey(sessionDO.getUserId());
//        if (userDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.USER_NOT_EXIST);
//        }
//        courtDO.setUserId(userDO.getId());
//        try {
//            return courtBiz.create(courtDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//    @AppRequestMapping(apiName = "court.update", apiVersion = "1.0")
//    public BizResult update(@AppRequestParam("sid") String sid, CourtDO courtDO) {
//        if (!StringUtils.hasText(sid) || courtDO == null || courtDO.getId() < 1) {
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
//        //校验场地是否是自己的
//        CourtDO courtDOTemp = courtDOMapper.selectByPrimaryKey(courtDO.getId());
//        if (courtDOTemp == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
//        }
//        if (courtDOTemp.getUserId() != userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        courtDO.setUserId(userDO.getId());
//        try {
//            return courtBiz.update(courtDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//
//    @AppRequestMapping(apiName = "court.delete", apiVersion = "1.0")
//    public BizResult delete(@AppRequestParam("sid") String sid, @AppRequestParam("id") long id) {
//        if (!StringUtils.hasText(sid) || id < 1) {
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
//        //校验场地是否是自己的
//        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(id);
//        if (courtDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
//        }
//        if (courtDO.getUserId() != userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        try {
//            return courtBiz.update(courtDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//
//    @AppRequestMapping(apiName = "court.apply", apiVersion = "1.0")
//    public BizResult apply(@AppRequestParam("sid") String sid, @AppRequestParam("courtId") long courtId, @AppRequestParam("applyTime") String applyTime) {
//        if (!StringUtils.hasText(sid) || courtId < 1 || !StringUtils.hasText(applyTime)) {
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
//        //校验场地是否是自己的
//        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtId);
//        if (courtDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
//        }
//        if (courtDO.getUserId() == userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        //判断是否已经申请过，同一天只能申请一个场地
//
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateHelper.defaultPattern);
//            Date parseApplyTime = simpleDateFormat.parse(applyTime);
//            if (parseApplyTime.before(new Date())) {
//                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_TIME_ERROR);
//            }
//            if (parseApplyTime.after(DateHelper.get7Time())) {
//                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_TIME_ERROR_WITH7);
//            }
//            //TODO 校验场地是否开放预约
//
////            CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
////            courtApplyDOCriteria.createCriteria().andBookingTimeBetween(DateHelper.getStartTime(applyTime), DateHelper.getEndTime(applyTime))
////                    .andUserIdEqualTo(userDO.getId())
////                    .andStatusNotEqualTo(ApplyStatusEnum.REJECT.value);
////            List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
////            if (!org.springframework.util.CollectionUtils.isEmpty(courtApplyDOs)) {
////                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_DULPLICATE);
////            }
//            CourtApplyDO courtApplyDO = new CourtApplyDO();
//            courtApplyDO.setUserId(userDO.getId());
//            courtApplyDO.setCourtId(courtId);
//            courtApplyDO.setGmtCreate(new Date());
//            courtApplyDO.setGmtModify(new Date());
//            courtApplyDO.setStatus(ApplyStatusEnum.INIT.value);
//           // courtApplyDO.setBookingDate();
//            return courtApplyBiz.create(courtApplyDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//
//
//
//    @AppRequestMapping(apiName = "court.cancelApply", apiVersion = "1.0")
//    public BizResult cancelApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") long applyId) {
//        if (!StringUtils.hasText(sid) || applyId < 1) {
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
//        //记录是否存在
//        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
//        if (courtApplyDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
//        }
//        if (courtApplyDO.getUserId() != userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        try {
//
//            return courtApplyBiz.delete(applyId);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//
//    @AppRequestMapping(apiName = "court.passApply", apiVersion = "1.0")
//    public BizResult passApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") long applyId) {
//        if (!StringUtils.hasText(sid) || applyId < 1) {
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
//        //记录是否存在
//        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
//        if (courtApplyDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
//        }
//        //检查场地是否是自己的
//        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
//        if(courtDO == null){
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
//        }
//        if (courtDO.getUserId() != userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        courtApplyDO.setStatus(ApplyStatusEnum.PASS.value);
//        try {
//            return courtApplyBiz.update(courtApplyDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }
//
//    @AppRequestMapping(apiName = "court.rejectApply", apiVersion = "1.0")
//    public BizResult rejectApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") long applyId) {
//        if (!StringUtils.hasText(sid) || applyId < 1) {
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
//        //记录是否存在
//        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
//        if (courtApplyDO == null) {
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
//        }
//        //检查场地是否是自己的
//        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
//        if(courtDO == null){
//            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
//        }
//        if (courtDO.getUserId() != userDO.getId()) {
//            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
//        }
//        courtApplyDO.setStatus(ApplyStatusEnum.REJECT.value);
//        try {
//            return courtApplyBiz.update(courtApplyDO);
//        } catch (Exception e) {
//            log.error("create court error", e);
//        }
//        return BizResultHelper.newCommonError();
//    }

//    @AppRequestMapping(apiName = "court.queryBookingList", apiVersion = "1.0")
//    public BizResult queryBookingList(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
//        if (!StringUtils.hasText(sid)) {
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
//        BaseQuery baseQuery = new BaseQuery();
//        baseQuery.setPageNo(pageNo);
//        baseQuery.setPageSize(pageSize);
//        CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
//        courtApplyDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
//        courtApplyDOCriteria.setOrderByClause(" gmt_create desc");
//        List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
//        int count = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
//        baseQuery.setTotalCount(count);
//        List<CourtApplyVO> courtApplyVOList = new ArrayList<CourtApplyVO>(courtApplyDOs.size());
//        for(CourtApplyDO courtApplyDO:courtApplyDOs){
//            CourtApplyVO courtApplyVO = new CourtApplyVO(courtApplyDO);
//            UserDO tempUserDO = userDOMapper.selectByPrimaryKey(courtApplyDO.getUserId());
//            if(tempUserDO!=null){
//                courtApplyVO.setApplyRealName(tempUserDO.getRealName());
//                courtApplyVO.setApplyUserName(tempUserDO.getUserName());
//            }
//        }
//        bizResult.data.put("courtApplyList",courtApplyVOList);
//        bizResult.data.put("query",baseQuery);
//        bizResult.success  = true;
//        return bizResult;
//    }
//
//
//    @AppRequestMapping(apiName = "court.queryBookingListForReview", apiVersion = "1.0")
//    public BizResult queryBookingListForReview(@AppRequestParam("sid")String sid,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
//        if (!StringUtils.hasText(sid)) {
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
//        BaseQuery baseQuery = new BaseQuery();
//        baseQuery.setPageNo(pageNo);
//        baseQuery.setPageSize(pageSize);
//        //查询我的场地
//        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
//        courtDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
//        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
//        if(org.springframework.util.CollectionUtils.isEmpty(courtDOs)){
//            bizResult.data.put("courtApplyList", Collections.emptyList());
//            bizResult.data.put("query",baseQuery);
//            bizResult.success  = true;
//            return bizResult;
//        }
//
//
//        List<Long> courtIdList = CollectionHelper.transformList(courtDOs,new Transformer<CourtDO, Long>() {
//            public Long transform(CourtDO object) {
//                return object.getId();
//            }
//        });
//        CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
//        courtApplyDOCriteria.createCriteria().andCourtIdIn(courtIdList);
//        courtApplyDOCriteria.setOrderByClause(" gmt_create desc");
//        List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
//        int count = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
//        baseQuery.setTotalCount(count);
//        List<CourtApplyVO> courtApplyVOList = new ArrayList<CourtApplyVO>(courtApplyDOs.size());
//        for(CourtApplyDO courtApplyDO:courtApplyDOs){
//            CourtApplyVO courtApplyVO = new CourtApplyVO(courtApplyDO);
//            UserDO tempUserDO = userDOMapper.selectByPrimaryKey(courtApplyDO.getUserId());
//            if(tempUserDO!=null){
//                courtApplyVO.setApplyRealName(tempUserDO.getRealName());
//                courtApplyVO.setApplyUserName(tempUserDO.getUserName());
//            }
//        }
//        bizResult.data.put("courtApplyList",courtApplyVOList);
//        bizResult.data.put("query",baseQuery);
//        bizResult.success  = true;
//        return bizResult;
//    }

    @AppRequestMapping(apiName = "court.queryList", apiVersion = "1.0")
    @Override
    public BizResult queryList(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo")  int pageNo,@AppRequestParam("pageSize")  int pageSize) {
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
        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        courtDOCriteria.setPageSize(baseQuery.getPageSize());
        courtDOCriteria.setStartRow(baseQuery.getStartRow());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        int i = courtDOMapper.countByExample(courtDOCriteria);
        baseQuery.setTotalCount(i);
        List<CourtVO> courtVOList = new ArrayList<CourtVO>();
        for(CourtDO courtDO:courtDOs){
            CourtVO courtVO = new CourtVO();
            courtVO.setCourt(courtDO);
            CourtSiteDOCriteria courtSiteDOCriteria = new CourtSiteDOCriteria();
            courtSiteDOCriteria.createCriteria().andCourtIdEqualTo(courtDO.getId());
            List<CourtSiteDO> courtSiteDOs = courtSiteDOMapper.selectByExample(courtSiteDOCriteria);
            List<CourtSiteVO> courtSiteVOList = new ArrayList<CourtSiteVO>();
            for(CourtSiteDO courtSiteDO:courtSiteDOs){
                CourtSiteVO courtSiteVO = new CourtSiteVO();
                courtSiteVO.setCourtSite(courtSiteDO);
                courtSiteVOList.add(courtSiteVO);
            }
            courtVO.setCourtSiteList(courtSiteVOList);
            courtVOList.add(courtVO);

        }
        bizResult.data.put("list",courtVOList);
        bizResult.data.put("query",baseQuery);
        bizResult.success  = true;
        return bizResult;
    }

    @AppRequestMapping(apiName = "court.queryListByName", apiVersion = "1.0")
    @Override
    public BizResult queryListByName(@AppRequestParam("sid") String sid,@AppRequestParam("name") String name,@AppRequestParam("pageNo")  int pageNo,@AppRequestParam("pageSize")  int pageSize) {
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
        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        if(StringUtils.hasText(name)) {
            courtDOCriteria.createCriteria().andNameLike("%" + name + "%");
        }
        courtDOCriteria.setPageSize(baseQuery.getPageSize());
        courtDOCriteria.setStartRow(baseQuery.getStartRow());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        int i = courtDOMapper.countByExample(courtDOCriteria);
        baseQuery.setTotalCount(i);
        List<CourtVO> courtVOList = new ArrayList<CourtVO>();
        for(CourtDO courtDO:courtDOs){
            CourtVO courtVO = new CourtVO();
            courtVO.setCourt(courtDO);
            CourtSiteDOCriteria courtSiteDOCriteria = new CourtSiteDOCriteria();
            courtSiteDOCriteria.createCriteria().andCourtIdEqualTo(courtDO.getId());
            List<CourtSiteDO> courtSiteDOs = courtSiteDOMapper.selectByExample(courtSiteDOCriteria);
            List<CourtSiteVO> courtSiteVOList = new ArrayList<CourtSiteVO>();
            for(CourtSiteDO courtSiteDO:courtSiteDOs){
                CourtSiteVO courtSiteVO = new CourtSiteVO();
                courtSiteVO.setCourtSite(courtSiteDO);
                courtSiteVOList.add(courtSiteVO);
            }
            courtVO.setCourtSiteList(courtSiteVOList);
            courtVOList.add(courtVO);

        }
        bizResult.data.put("list",courtVOList);
        bizResult.data.put("query",baseQuery);
        bizResult.success  = true;
        return bizResult;
    }

    @AppRequestMapping(apiName = "court.querySiteForBooking", apiVersion = "1.0")
    @Override
    public BizResult querySiteForBooking(@AppRequestParam("sid") String sid, @AppRequestParam("siteId") long siteId) {
        if (!StringUtils.hasText(sid)|| siteId < 1) {
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
        CourtSiteDO courtSiteDO = courtSiteDOMapper.selectByPrimaryKey(siteId);
        if(courtSiteDO == null){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_SITE_NOT_EXIST);

        }

        String openTemplate = courtSiteDO.getOpenTemplate();
        List<OpenTemplate> templateList = JSON.parseArray(openTemplate,OpenTemplate.class);

        Map<Integer, List<CourtSiteSectionDO>> map = new HashMap<Integer, List<CourtSiteSectionDO>>();
        for(OpenTemplate openTemplate1:templateList){
            map.put(openTemplate1.getWeek(),openTemplate1.getTimeSection());
        }
        CourtSiteVO courtSiteVO = new CourtSiteVO();
        courtSiteVO.setCourtSite(courtSiteDO);
        List<BookInfoVO> bookInfoVOList = new ArrayList<BookInfoVO>();

       for(int i=0,len=7;i<len;++i){
           bookInfoVOList.add(fillBookInfo(i,courtSiteDO,map));
       }

        bizResult.data.put("list",bookInfoVOList);
        bizResult.success  = true;
        return bizResult;
    }

    private BookInfoVO fillBookInfo(int day,CourtSiteDO courtSiteDO,Map<Integer, List<CourtSiteSectionDO>> map){
        BookInfoVO bookInfoVO = new BookInfoVO();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,day);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        Date statDate = calendar.getTime();
        bookInfoVO.setDate(statDate.getTime());
        bookInfoVO.setDateCN(DateHelper.formatYMDCN(statDate));
        int week = calendar.get(Calendar.DAY_OF_WEEK)/7;
        List<CourtSiteSectionDO> courtSiteSectionDODemo = map.get(week);
        List<TimeSectionVO> sectionVOList = new ArrayList<TimeSectionVO>();
        //初始化今天的数据
        String date = DateHelper.formatYMD(statDate);
        String dateCN = DateHelper.formatYMDCN(statDate);
        for(CourtSiteSectionDO courtSiteSectionDO:courtSiteSectionDODemo){
            if(day ==0 && hasExpire(courtSiteSectionDO)){
                continue;
            }
            TimeSectionVO timeSectionVO = new TimeSectionVO();
            CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
            courtApplyDOCriteria.createCriteria().andBookingDateEqualTo(statDate)
                    .andBookingTimeEqualTo(courtSiteSectionDO.getTimeInfo());
            int count = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
            if(count > 0) {
                timeSectionVO.setBookStatus(true);
            }
            String timeInfo = courtSiteSectionDO.getTimeInfo();
            String[] split = timeInfo.split("-");
            String startCN = dateCN + " " + split[0];
            String endCN = dateCN + " " + split[1];
            timeSectionVO.setEndTimeCN(endCN);
            timeSectionVO.setStartTimeCN(startCN);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            try {
                timeSectionVO.setStartTime(simpleDateFormat.parse(startCN).getTime());
                simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                timeSectionVO.setEndTime(simpleDateFormat.parse(endCN).getTime());
            }catch(Exception e){

            }
            timeSectionVO.setSiteNo(courtSiteDO.getCourtId()+"-"+courtSiteDO.getId()+"-"+date+"-"+courtSiteSectionDO.getTimeInfo());
            sectionVOList.add(timeSectionVO);
        }
        bookInfoVO.setTimeSectionList(sectionVOList);
        return bookInfoVO;
    }



    private boolean hasExpire(CourtSiteSectionDO courtSiteSectionDO){

        String timeInfo = courtSiteSectionDO.getTimeInfo();
        String[] split = timeInfo.split("-");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        try {
            Date parse = simpleDateFormat.parse(split[0]);
            if(new Date().before(parse)){
                return false;
            }
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
