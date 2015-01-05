package guda.ball.biz.impl;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.ball.biz.TeamRecruitBiz;
import guda.ball.dao.TeamRecruitDOMapper;
import guda.ball.dao.domain.TeamRecruitDO;
import guda.ball.dao.domain.TeamRecruitDOCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class TeamRecruitBizImpl implements TeamRecruitBiz{

    private final static Logger logger = LoggerFactory.getLogger(TeamRecruitBizImpl.class);

    @Autowired
    private TeamRecruitDOMapper teamRecruitDOMapper;

    public BizResult detail(int id) {
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

    public BizResult delete(int id) {
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
        teamRecruitDO.setGmtModify(new Date());
        teamRecruitDO.setGmtCreate(new Date());
        teamRecruitDO.setIsDeleted(0);
        BizResult bizResult = new BizResult();
        try {
            int id = teamRecruitDOMapper.insert(teamRecruitDO);
            bizResult.data.put("count", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create TeamRecruit error", e);
        }
        return bizResult;
    }

    public BizResult update(TeamRecruitDO teamRecruitDO) {
        BizResult bizResult = new BizResult();
        try {
            int id = teamRecruitDOMapper.updateByPrimaryKeySelective(teamRecruitDO);
            bizResult.data.put("count", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update TeamRecruit error", e);
        }
        return bizResult;
    }

    }
