package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.TeamApplyBiz;
import guda.ball.dao.TeamApplyDOMapper;
import guda.ball.dao.domain.TeamApplyDO;
import guda.ball.dao.domain.TeamApplyDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TeamApplyBizImpl implements TeamApplyBiz{

    private final static Logger logger = LoggerFactory.getLogger(TeamApplyBizImpl.class);

    @Autowired
    private TeamApplyDOMapper teamApplyDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TeamApplyDO teamApplyDO = teamApplyDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("teamApplyDO", teamApplyDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TeamApply error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TeamApplyDOCriteria teamApplyDOCriteria = new TeamApplyDOCriteria();
            teamApplyDOCriteria.setStartRow(baseQuery.getStartRow());
            teamApplyDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = teamApplyDOMapper.countByExample(teamApplyDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TeamApplyDO> teamApplyDOList = teamApplyDOMapper.selectByExample(teamApplyDOCriteria);
            bizResult.data.put("teamApplyDOList", teamApplyDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TeamApply list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            teamApplyDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete teamApply error", e);
        }
        return bizResult;
    }

    public BizResult create(TeamApplyDO teamApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = teamApplyDOMapper.insert(teamApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TeamApply error", e);
        }
        return bizResult;
    }

    public BizResult update(TeamApplyDO teamApplyDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = teamApplyDOMapper.updateByPrimaryKeySelective(teamApplyDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TeamApply error", e);
        }
        return bizResult;
    }

    }
