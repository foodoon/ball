package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChallengeApplyDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow = -1;

    protected int pageSize = -1;

    public ChallengeApplyDOCriteria() {
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

        public Criteria andChallengeIdIsNull() {
            addCriterion("challenge_id is null");
            return (Criteria) this;
        }

        public Criteria andChallengeIdIsNotNull() {
            addCriterion("challenge_id is not null");
            return (Criteria) this;
        }

        public Criteria andChallengeIdEqualTo(Long value) {
            addCriterion("challenge_id =", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdNotEqualTo(Long value) {
            addCriterion("challenge_id <>", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdGreaterThan(Long value) {
            addCriterion("challenge_id >", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("challenge_id >=", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdLessThan(Long value) {
            addCriterion("challenge_id <", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdLessThanOrEqualTo(Long value) {
            addCriterion("challenge_id <=", value, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdIn(List<Long> values) {
            addCriterion("challenge_id in", values, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdNotIn(List<Long> values) {
            addCriterion("challenge_id not in", values, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdBetween(Long value1, Long value2) {
            addCriterion("challenge_id between", value1, value2, "challengeId");
            return (Criteria) this;
        }

        public Criteria andChallengeIdNotBetween(Long value1, Long value2) {
            addCriterion("challenge_id not between", value1, value2, "challengeId");
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

        public Criteria andTeamIdEqualTo(Long value) {
            addCriterion("team_id =", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotEqualTo(Long value) {
            addCriterion("team_id <>", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThan(Long value) {
            addCriterion("team_id >", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdGreaterThanOrEqualTo(Long value) {
            addCriterion("team_id >=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThan(Long value) {
            addCriterion("team_id <", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdLessThanOrEqualTo(Long value) {
            addCriterion("team_id <=", value, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdIn(List<Long> values) {
            addCriterion("team_id in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotIn(List<Long> values) {
            addCriterion("team_id not in", values, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdBetween(Long value1, Long value2) {
            addCriterion("team_id between", value1, value2, "teamId");
            return (Criteria) this;
        }

        public Criteria andTeamIdNotBetween(Long value1, Long value2) {
            addCriterion("team_id not between", value1, value2, "teamId");
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

        public Criteria andAcceptIsNull() {
            addCriterion("accept is null");
            return (Criteria) this;
        }

        public Criteria andAcceptIsNotNull() {
            addCriterion("accept is not null");
            return (Criteria) this;
        }

        public Criteria andAcceptEqualTo(Integer value) {
            addCriterion("accept =", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotEqualTo(Integer value) {
            addCriterion("accept <>", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptGreaterThan(Integer value) {
            addCriterion("accept >", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptGreaterThanOrEqualTo(Integer value) {
            addCriterion("accept >=", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptLessThan(Integer value) {
            addCriterion("accept <", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptLessThanOrEqualTo(Integer value) {
            addCriterion("accept <=", value, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptIn(List<Integer> values) {
            addCriterion("accept in", values, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotIn(List<Integer> values) {
            addCriterion("accept not in", values, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptBetween(Integer value1, Integer value2) {
            addCriterion("accept between", value1, value2, "accept");
            return (Criteria) this;
        }

        public Criteria andAcceptNotBetween(Integer value1, Integer value2) {
            addCriterion("accept not between", value1, value2, "accept");
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