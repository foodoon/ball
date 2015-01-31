package guda.ball.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import guda.ball.biz.TeamBiz;
import guda.ball.dao.TeamDOMapper;
import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.TeamDOCriteria;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;

public class TeamBizImpl implements TeamBiz{

    private final static Logger logger = LoggerFactory.getLogger(TeamBizImpl.class);

    @Autowired
    private TeamDOMapper teamDOMapper;

    public BizResult detail(long id) {
        BizResult bizResult = new BizResult();
        try{
            TeamDO teamDO = teamDOMapper.selectByPrimaryKey(id);
            bizResult.data.put("teamDO", teamDO);
            bizResult.success = true;
        }catch(Exception e){
            logger.error("query Team error",e);
        }
        return bizResult;
}

    public BizResult list(BaseQuery baseQuery) {
        BizResult bizResult = new BizResult();
        try {
            TeamDOCriteria teamDOCriteria = new TeamDOCriteria();
            teamDOCriteria.setStartRow(baseQuery.getStartRow());
            teamDOCriteria.setPageSize(baseQuery.getPageSize());
            int totalCount = teamDOMapper.countByExample(teamDOCriteria);
            baseQuery.setTotalCount(totalCount);
            List<TeamDO> teamDOList = teamDOMapper.selectByExample(teamDOCriteria);
            bizResult.data.put("teamDOList", teamDOList);
            bizResult.data.put("query", baseQuery);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("view Team list error", e);
        }
            return bizResult;
     }

    public BizResult delete(long id) {
        BizResult bizResult = new BizResult();
        try {
            teamDOMapper.deleteByPrimaryKey(id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("delete team error", e);
        }
        return bizResult;
    }

    public BizResult create(TeamDO teamDO) {
        BizResult bizResult = new BizResult();
        try {
            long id = teamDOMapper.insert(teamDO);
            bizResult.data.put("id", id);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("create Team error", e);
        }
        return bizResult;
    }

    public BizResult update(TeamDO teamDO) {
        BizResult bizResult = new BizResult();
        try {
            int count = teamDOMapper.updateByPrimaryKeySelective(teamDO);
            bizResult.data.put("count", count);
            bizResult.success = true;
        } catch (Exception e) {
            logger.error("update Team error", e);
        }
        return bizResult;
    }

    }
