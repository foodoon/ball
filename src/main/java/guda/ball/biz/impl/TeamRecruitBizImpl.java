package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.TeamRecruitBiz;
import guda.ball.dao.TeamRecruitDOMapper;
import guda.ball.dao.domain.TeamRecruitDO;
import guda.ball.dao.domain.TeamRecruitDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TeamRecruitBizImpl implements TeamRecruitBiz{

    private final static Logger logger = LoggerFactory.getLogger(TeamRecruitBizImpl.class);

    @Autowired
    private TeamRecruitDOMapper teamRecruitDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TeamRecruitDO teamRecruitDO = teamRecruitDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("teamRecruitDO", teamRecruitDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query TeamRecruit error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TeamRecruitDOCriteria teamRecruitDOCriteria = new TeamRecruitDOCriteria();
            teamRecruitDOCriteria.setStartRow(baseQuery.getStartRow());
            teamRecruitDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = teamRecruitDOMapper.countByExample(teamRecruitDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TeamRecruitDO> teamRecruitDOList = teamRecruitDOMapper.selectByExample(teamRecruitDOCriteria);
            bizResult.data.put("teamRecruitDOList", teamRecruitDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view TeamRecruit list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            teamRecruitDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete teamRecruit error", e);
        }
        return bizResult;
    }

    public BizResult create(TeamRecruitDO teamRecruitDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = teamRecruitDOMapper.insert(teamRecruitDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TeamRecruit error", e);
        }
        return bizResult;
    }

    public BizResult update(TeamRecruitDO teamRecruitDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = teamRecruitDOMapper.updateByPrimaryKeySelective(teamRecruitDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TeamRecruit error", e);
        }
        return bizResult;
    }

    }
