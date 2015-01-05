package guda.ball.biz.service.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.CourtApplyBiz;
import guda.ball.biz.CourtBiz;
import guda.ball.biz.SessionBiz;
import guda.ball.biz.entity.CourtApplyVO;
import guda.ball.biz.service.CourtService;
import guda.ball.dao.CourtApplyDOMapper;
import guda.ball.dao.CourtDOMapper;
import guda.ball.dao.UserDOMapper;
import guda.ball.dao.domain.*;
import guda.ball.util.*;
import guda.ball.util.enums.ApplyStatusEnum;
import guda.ball.util.enums.CourtTypeEnum;
import guda.ball.util.enums.OpenTimeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by foodoon on 2014/8/3.
 */
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

    @AppRequestMapping(apiName = "court.create", apiVersion = "1.0")
    public BizResult create(@AppRequestParam("sid") String sid, CourtDO courtDO) {
        if (!StringUtils.hasText(sid) || courtDO == null || !StringUtils.hasText(courtDO.getName())
                || !StringUtils.hasText(courtDO.getAddress())
                || !StringUtils.hasText(courtDO.getOpenTime())
                || !StringUtils.hasText(courtDO.getType())) {
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        if(CourtTypeEnum.getByValue(courtDO.getType()) == null){
            return BizResultHelper.newResultCode(CommonResultCode.PARAM_MISS);
        }
        if(OpenTimeEnum.getByValue(courtDO.getOpenTime()) == null){
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
        courtDO.setUserId(userDO.getId());
        try {
            return courtBiz.create(courtDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }
    @AppRequestMapping(apiName = "court.update", apiVersion = "1.0")
    public BizResult update(@AppRequestParam("sid") String sid, CourtDO courtDO) {
        if (!StringUtils.hasText(sid) || courtDO == null || courtDO.getId() < 1) {
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
        //校验场地是否是自己的
        CourtDO courtDOTemp = courtDOMapper.selectByPrimaryKey(courtDO.getId());
        if (courtDOTemp == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if (courtDOTemp.getUserId() != userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        courtDO.setUserId(userDO.getId());
        try {
            return courtBiz.update(courtDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "court.delete", apiVersion = "1.0")
    public BizResult delete(@AppRequestParam("sid") String sid, @AppRequestParam("id") int id) {
        if (!StringUtils.hasText(sid) || id < 1) {
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
        //校验场地是否是自己的
        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(id);
        if (courtDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if (courtDO.getUserId() != userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        try {
            return courtBiz.update(courtDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "court.apply", apiVersion = "1.0")
    public BizResult apply(@AppRequestParam("sid") String sid, @AppRequestParam("courtId") int courtId, @AppRequestParam("applyTime") String applyTime) {
        if (!StringUtils.hasText(sid) || courtId < 1 || !StringUtils.hasText(applyTime)) {
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
        //校验场地是否是自己的
        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtId);
        if (courtDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if (courtDO.getUserId() == userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        //判断是否已经申请过，同一天只能申请一个场地

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateHelper.defaultPattern);
            Date parseApplyTime = simpleDateFormat.parse(applyTime);
            if (parseApplyTime.before(new Date())) {
                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_TIME_ERROR);
            }
            if (parseApplyTime.after(DateHelper.get7Time())) {
                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_TIME_ERROR_WITH7);
            }
            //TODO 校验场地是否开放预约
            if(!checkTime(courtDO,parseApplyTime)){
                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_TIME_ERROR_WITH7);
            }
            CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
            courtApplyDOCriteria.createCriteria().andBookingTimeBetween(DateHelper.getStartTime(applyTime), DateHelper.getEndTime(applyTime))
                    .andUserIdEqualTo(userDO.getId())
                    .andStatusNotEqualTo(ApplyStatusEnum.REJECT.value);
            List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
            if (!org.springframework.util.CollectionUtils.isEmpty(courtApplyDOs)) {
                return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_DULPLICATE);
            }
            CourtApplyDO courtApplyDO = new CourtApplyDO();
            courtApplyDO.setUserId(userDO.getId());
            courtApplyDO.setCourtId(courtId);
            courtApplyDO.setGmtCreate(new Date());
            courtApplyDO.setGmtModify(new Date());
            courtApplyDO.setStatus(ApplyStatusEnum.INIT.value);
            courtApplyDO.setBookingTime(parseApplyTime);
            return courtApplyBiz.create(courtApplyDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    private boolean checkTime(CourtDO courtDO, Date applyTime) {
        if (OpenTimeEnum.ALL.value.equals(courtDO.getOpenTime())) {
            return true;
        } else if (OpenTimeEnum.WORKDAY.value.equals(courtDO.getOpenTime()) && DateHelper.isWorkday(applyTime)) {
            return true;
        } else if (OpenTimeEnum.WEEKEND.value.equals(courtDO.getOpenTime()) && DateHelper.isWeekend(applyTime)) {
            return true;
        }
        return false;

    }

    @AppRequestMapping(apiName = "court.cancelApply", apiVersion = "1.0")
    public BizResult cancelApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") int applyId) {
        if (!StringUtils.hasText(sid) || applyId < 1) {
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
        //记录是否存在
        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
        if (courtApplyDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
        }
        if (courtApplyDO.getUserId() != userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        try {

            return courtApplyBiz.delete(applyId);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "court.passApply", apiVersion = "1.0")
    public BizResult passApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") int applyId) {
        if (!StringUtils.hasText(sid) || applyId < 1) {
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
        //记录是否存在
        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
        if (courtApplyDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
        }
        //检查场地是否是自己的
        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
        if(courtDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if (courtDO.getUserId() != userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        courtApplyDO.setStatus(ApplyStatusEnum.PASS.value);
        try {
            return courtApplyBiz.update(courtApplyDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "court.rejectApply", apiVersion = "1.0")
    public BizResult rejectApply(@AppRequestParam("sid") String sid, @AppRequestParam("applyId") int applyId) {
        if (!StringUtils.hasText(sid) || applyId < 1) {
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
        //记录是否存在
        CourtApplyDO courtApplyDO = courtApplyDOMapper.selectByPrimaryKey(applyId);
        if (courtApplyDO == null) {
            return BizResultHelper.newResultCode(CommonResultCode.COURT_APPLY_NOT_EXIST);
        }
        //检查场地是否是自己的
        CourtDO courtDO = courtDOMapper.selectByPrimaryKey(courtApplyDO.getCourtId());
        if(courtDO == null){
            return BizResultHelper.newResultCode(CommonResultCode.COURT_NOT_EXIST);
        }
        if (courtDO.getUserId() != userDO.getId()) {
            return BizResultHelper.newResultCode(CommonResultCode.PERSSION_ERROR);
        }
        courtApplyDO.setStatus(ApplyStatusEnum.REJECT.value);
        try {
            return courtApplyBiz.update(courtApplyDO);
        } catch (Exception e) {
            log.error("create court error", e);
        }
        return BizResultHelper.newCommonError();
    }

    @AppRequestMapping(apiName = "court.queryBookingList", apiVersion = "1.0")
    public BizResult queryBookingList(@AppRequestParam("sid") String sid,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
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
        CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
        courtApplyDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        courtApplyDOCriteria.setOrderByClause("order by gmt_create desc");
        List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
        int count = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
        baseQuery.setTotalCount(count);
        List<CourtApplyVO> courtApplyVOList = new ArrayList<CourtApplyVO>(courtApplyDOs.size());
        for(CourtApplyDO courtApplyDO:courtApplyDOs){
            CourtApplyVO courtApplyVO = new CourtApplyVO(courtApplyDO);
            UserDO tempUserDO = userDOMapper.selectByPrimaryKey(courtApplyDO.getUserId());
            if(tempUserDO!=null){
                courtApplyVO.setApplyRealName(tempUserDO.getRealName());
                courtApplyVO.setApplyUserName(tempUserDO.getUserName());
            }
        }
        bizResult.data.put("courtApplyList",courtApplyVOList);
        bizResult.data.put("query",baseQuery);
        bizResult.success  = true;
        return bizResult;
    }

    @AppRequestMapping(apiName = "court.queryBookingListForReview", apiVersion = "1.0")
    public BizResult queryBookingListForReview(String sid,@AppRequestParam("pageNo")int pageNo,@AppRequestParam("pageSize")int pageSize) {
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
        //查询我的场地
        CourtDOCriteria courtDOCriteria = new CourtDOCriteria();
        courtDOCriteria.createCriteria().andUserIdEqualTo(userDO.getId());
        List<CourtDO> courtDOs = courtDOMapper.selectByExample(courtDOCriteria);
        if(org.springframework.util.CollectionUtils.isEmpty(courtDOs)){
            bizResult.data.put("courtApplyList", Collections.emptyList());
            bizResult.data.put("query",baseQuery);
            bizResult.success  = true;
            return bizResult;
        }


        List<Integer> courtIdList = CollectionHelper.transformList(courtDOs,new Transformer<CourtDO, Integer>() {
            public Integer transform(CourtDO object) {
                return object.getId();
            }
        });
        CourtApplyDOCriteria courtApplyDOCriteria = new CourtApplyDOCriteria();
        courtApplyDOCriteria.createCriteria().andCourtIdIn(courtIdList);
        courtApplyDOCriteria.setOrderByClause("order by gmt_create desc");
        List<CourtApplyDO> courtApplyDOs = courtApplyDOMapper.selectByExample(courtApplyDOCriteria);
        int count = courtApplyDOMapper.countByExample(courtApplyDOCriteria);
        baseQuery.setTotalCount(count);
        List<CourtApplyVO> courtApplyVOList = new ArrayList<CourtApplyVO>(courtApplyDOs.size());
        for(CourtApplyDO courtApplyDO:courtApplyDOs){
            CourtApplyVO courtApplyVO = new CourtApplyVO(courtApplyDO);
            UserDO tempUserDO = userDOMapper.selectByPrimaryKey(courtApplyDO.getUserId());
            if(tempUserDO!=null){
                courtApplyVO.setApplyRealName(tempUserDO.getRealName());
                courtApplyVO.setApplyUserName(tempUserDO.getUserName());
            }
        }
        bizResult.data.put("courtApplyList",courtApplyVOList);
        bizResult.data.put("query",baseQuery);
        bizResult.success  = true;
        return bizResult;
    }
}
