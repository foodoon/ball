package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChallengeDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow = -1;

    protected int pageSize = -1;

    public ChallengeDOCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setStartRow(int startRow) {
        this.startRow=startRow;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setPageSize(int pageSize) {
        this.pageSize=pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdIsNull() {
            addCriterion("request_team_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdIsNotNull() {
            addCriterion("request_team_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdEqualTo(Long value) {
            addCriterion("request_team_id =", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdNotEqualTo(Long value) {
            addCriterion("request_team_id <>", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdGreaterThan(Long value) {
            addCriterion("request_team_id >", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("request_team_id >=", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdLessThan(Long value) {
            addCriterion("request_team_id <", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdLessThanOrEqualTo(Long value) {
            addCriterion("request_team_id <=", value, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdIn(List<Long> values) {
            addCriterion("request_team_id in", values, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdNotIn(List<Long> values) {
            addCriterion("request_team_id not in", values, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdBetween(Long value1, Long value2) {
            addCriterion("request_team_id between", value1, value2, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andRequestTeamIdNotBetween(Long value1, Long value2) {
            addCriterion("request_team_id not between", value1, value2, "requestTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdIsNull() {
            addCriterion("apply_team_id is null");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdIsNotNull() {
            addCriterion("apply_team_id is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdEqualTo(Long value) {
            addCriterion("apply_team_id =", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdNotEqualTo(Long value) {
            addCriterion("apply_team_id <>", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdGreaterThan(Long value) {
            addCriterion("apply_team_id >", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("apply_team_id >=", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdLessThan(Long value) {
            addCriterion("apply_team_id <", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdLessThanOrEqualTo(Long value) {
            addCriterion("apply_team_id <=", value, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdIn(List<Long> values) {
            addCriterion("apply_team_id in", values, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdNotIn(List<Long> values) {
            addCriterion("apply_team_id not in", values, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdBetween(Long value1, Long value2) {
            addCriterion("apply_team_id between", value1, value2, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andApplyTeamIdNotBetween(Long value1, Long value2) {
            addCriterion("apply_team_id not between", value1, value2, "applyTeamId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdIsNull() {
            addCriterion("court_apply_id is null");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdIsNotNull() {
            addCriterion("court_apply_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdEqualTo(Long value) {
            addCriterion("court_apply_id =", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdNotEqualTo(Long value) {
            addCriterion("court_apply_id <>", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdGreaterThan(Long value) {
            addCriterion("court_apply_id >", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("court_apply_id >=", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdLessThan(Long value) {
            addCriterion("court_apply_id <", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdLessThanOrEqualTo(Long value) {
            addCriterion("court_apply_id <=", value, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdIn(List<Long> values) {
            addCriterion("court_apply_id in", values, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdNotIn(List<Long> values) {
            addCriterion("court_apply_id not in", values, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdBetween(Long value1, Long value2) {
            addCriterion("court_apply_id between", value1, value2, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andCourtApplyIdNotBetween(Long value1, Long value2) {
            addCriterion("court_apply_id not between", value1, value2, "courtApplyId");
            return (Criteria) this;
        }

        public Criteria andChallengeDescIsNull() {
            addCriterion("challenge_desc is null");
            return (Criteria) this;
        }

        public Criteria andChallengeDescIsNotNull() {
            addCriterion("challenge_desc is not null");
            return (Criteria) this;
        }

        public Criteria andChallengeDescEqualTo(String value) {
            addCriterion("challenge_desc =", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescNotEqualTo(String value) {
            addCriterion("challenge_desc <>", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescGreaterThan(String value) {
            addCriterion("challenge_desc >", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescGreaterThanOrEqualTo(String value) {
            addCriterion("challenge_desc >=", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescLessThan(String value) {
            addCriterion("challenge_desc <", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescLessThanOrEqualTo(String value) {
            addCriterion("challenge_desc <=", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescLike(String value) {
            addCriterion("challenge_desc like", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescNotLike(String value) {
            addCriterion("challenge_desc not like", value, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescIn(List<String> values) {
            addCriterion("challenge_desc in", values, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescNotIn(List<String> values) {
            addCriterion("challenge_desc not in", values, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescBetween(String value1, String value2) {
            addCriterion("challenge_desc between", value1, value2, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeDescNotBetween(String value1, String value2) {
            addCriterion("challenge_desc not between", value1, value2, "challengeDesc");
            return (Criteria) this;
        }

        public Criteria andChallengeResultIsNull() {
            addCriterion("challenge_result is null");
            return (Criteria) this;
        }

        public Criteria andChallengeResultIsNotNull() {
            addCriterion("challenge_result is not null");
            return (Criteria) this;
        }

        public Criteria andChallengeResultEqualTo(String value) {
            addCriterion("challenge_result =", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultNotEqualTo(String value) {
            addCriterion("challenge_result <>", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultGreaterThan(String value) {
            addCriterion("challenge_result >", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultGreaterThanOrEqualTo(String value) {
            addCriterion("challenge_result >=", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultLessThan(String value) {
            addCriterion("challenge_result <", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultLessThanOrEqualTo(String value) {
            addCriterion("challenge_result <=", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultLike(String value) {
            addCriterion("challenge_result like", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultNotLike(String value) {
            addCriterion("challenge_result not like", value, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultIn(List<String> values) {
            addCriterion("challenge_result in", values, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultNotIn(List<String> values) {
            addCriterion("challenge_result not in", values, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultBetween(String value1, String value2) {
            addCriterion("challenge_result between", value1, value2, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andChallengeResultNotBetween(String value1, String value2) {
            addCriterion("challenge_result not between", value1, value2, "challengeResult");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andGoalCountIsNull() {
            addCriterion("goal_count is null");
            return (Criteria) this;
        }

        public Criteria andGoalCountIsNotNull() {
            addCriterion("goal_count is not null");
            return (Criteria) this;
        }

        public Criteria andGoalCountEqualTo(Integer value) {
            addCriterion("goal_count =", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountNotEqualTo(Integer value) {
            addCriterion("goal_count <>", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountGreaterThan(Integer value) {
            addCriterion("goal_count >", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("goal_count >=", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountLessThan(Integer value) {
            addCriterion("goal_count <", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountLessThanOrEqualTo(Integer value) {
            addCriterion("goal_count <=", value, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountIn(List<Integer> values) {
            addCriterion("goal_count in", values, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountNotIn(List<Integer> values) {
            addCriterion("goal_count not in", values, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountBetween(Integer value1, Integer value2) {
            addCriterion("goal_count between", value1, value2, "goalCount");
            return (Criteria) this;
        }

        public Criteria andGoalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("goal_count not between", value1, value2, "goalCount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}