package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNull() {
            addCriterion("team_id is null");
            return (Criteria) this;
        }

        public Criteria andTeamIdIsNotNull() {
            addCriterion("team_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeamIdEqualTo(Integer value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Integer value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Integer value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Integer value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Integer value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Integer> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Integer> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Integer value1, Integer value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andCourtIdIsNull() {
            addCriterion("court_id is null");
            return (Criteria) this;
        }

        public Criteria andCourtIdIsNotNull() {
            addCriterion("court_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourtIdEqualTo(Integer value) {
            addCriterion("court_id =", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotEqualTo(Integer value) {
            addCriterion("court_id <>", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdGreaterThan(Integer value) {
            addCriterion("court_id >", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("court_id >=", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdLessThan(Integer value) {
            addCriterion("court_id <", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdLessThanOrEqualTo(Integer value) {
            addCriterion("court_id <=", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdIn(List<Integer> values) {
            addCriterion("court_id in", values, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotIn(List<Integer> values) {
            addCriterion("court_id not in", values, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdBetween(Integer value1, Integer value2) {
            addCriterion("court_id between", value1, value2, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("court_id not between", value1, value2, "courtId");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeIsNull() {
            addCriterion("challenge_time is null");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeIsNotNull() {
            addCriterion("challenge_time is not null");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeEqualTo(Date value) {
            addCriterionForJDBCDate("challenge_time =", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("challenge_time <>", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("challenge_time >", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("challenge_time >=", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeLessThan(Date value) {
            addCriterionForJDBCDate("challenge_time <", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("challenge_time <=", value, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeIn(List<Date> values) {
            addCriterionForJDBCDate("challenge_time in", values, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("challenge_time not in", values, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("challenge_time between", value1, value2, "challengeTime");
            return (Criteria) this;
        }

        public Criteria andChallengeTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("challenge_time not between", value1, value2, "challengeTime");
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

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Integer value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Integer value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Integer value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Integer value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Integer> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Integer> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
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