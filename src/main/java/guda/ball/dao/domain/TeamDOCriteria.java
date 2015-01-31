package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow = -1;

    protected int pageSize = -1;

    public TeamDOCriteria() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andTeamDescIsNull() {
            addCriterion("team_desc is null");
            return (Criteria) this;
        }

        public Criteria andTeamDescIsNotNull() {
            addCriterion("team_desc is not null");
            return (Criteria) this;
        }

        public Criteria andTeamDescEqualTo(String value) {
            addCriterion("team_desc =", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescNotEqualTo(String value) {
            addCriterion("team_desc <>", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescGreaterThan(String value) {
            addCriterion("team_desc >", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescGreaterThanOrEqualTo(String value) {
            addCriterion("team_desc >=", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescLessThan(String value) {
            addCriterion("team_desc <", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescLessThanOrEqualTo(String value) {
            addCriterion("team_desc <=", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescLike(String value) {
            addCriterion("team_desc like", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescNotLike(String value) {
            addCriterion("team_desc not like", value, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescIn(List<String> values) {
            addCriterion("team_desc in", values, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescNotIn(List<String> values) {
            addCriterion("team_desc not in", values, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescBetween(String value1, String value2) {
            addCriterion("team_desc between", value1, value2, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamDescNotBetween(String value1, String value2) {
            addCriterion("team_desc not between", value1, value2, "teamDesc");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNull() {
            addCriterion("team_type is null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIsNotNull() {
            addCriterion("team_type is not null");
            return (Criteria) this;
        }

        public Criteria andTeamTypeEqualTo(String value) {
            addCriterion("team_type =", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotEqualTo(String value) {
            addCriterion("team_type <>", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThan(String value) {
            addCriterion("team_type >", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("team_type >=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThan(String value) {
            addCriterion("team_type <", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLessThanOrEqualTo(String value) {
            addCriterion("team_type <=", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeLike(String value) {
            addCriterion("team_type like", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotLike(String value) {
            addCriterion("team_type not like", value, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeIn(List<String> values) {
            addCriterion("team_type in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotIn(List<String> values) {
            addCriterion("team_type not in", values, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeBetween(String value1, String value2) {
            addCriterion("team_type between", value1, value2, "teamType");
            return (Criteria) this;
        }

        public Criteria andTeamTypeNotBetween(String value1, String value2) {
            addCriterion("team_type not between", value1, value2, "teamType");
            return (Criteria) this;
        }

        public Criteria andCanJoinIsNull() {
            addCriterion("can_join is null");
            return (Criteria) this;
        }

        public Criteria andCanJoinIsNotNull() {
            addCriterion("can_join is not null");
            return (Criteria) this;
        }

        public Criteria andCanJoinEqualTo(Integer value) {
            addCriterion("can_join =", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinNotEqualTo(Integer value) {
            addCriterion("can_join <>", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinGreaterThan(Integer value) {
            addCriterion("can_join >", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinGreaterThanOrEqualTo(Integer value) {
            addCriterion("can_join >=", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinLessThan(Integer value) {
            addCriterion("can_join <", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinLessThanOrEqualTo(Integer value) {
            addCriterion("can_join <=", value, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinIn(List<Integer> values) {
            addCriterion("can_join in", values, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinNotIn(List<Integer> values) {
            addCriterion("can_join not in", values, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinBetween(Integer value1, Integer value2) {
            addCriterion("can_join between", value1, value2, "canJoin");
            return (Criteria) this;
        }

        public Criteria andCanJoinNotBetween(Integer value1, Integer value2) {
            addCriterion("can_join not between", value1, value2, "canJoin");
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

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andHomeCourtIsNull() {
            addCriterion("home_court is null");
            return (Criteria) this;
        }

        public Criteria andHomeCourtIsNotNull() {
            addCriterion("home_court is not null");
            return (Criteria) this;
        }

        public Criteria andHomeCourtEqualTo(String value) {
            addCriterion("home_court =", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtNotEqualTo(String value) {
            addCriterion("home_court <>", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtGreaterThan(String value) {
            addCriterion("home_court >", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtGreaterThanOrEqualTo(String value) {
            addCriterion("home_court >=", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtLessThan(String value) {
            addCriterion("home_court <", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtLessThanOrEqualTo(String value) {
            addCriterion("home_court <=", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtLike(String value) {
            addCriterion("home_court like", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtNotLike(String value) {
            addCriterion("home_court not like", value, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtIn(List<String> values) {
            addCriterion("home_court in", values, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtNotIn(List<String> values) {
            addCriterion("home_court not in", values, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtBetween(String value1, String value2) {
            addCriterion("home_court between", value1, value2, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andHomeCourtNotBetween(String value1, String value2) {
            addCriterion("home_court not between", value1, value2, "homeCourt");
            return (Criteria) this;
        }

        public Criteria andJerseyColorIsNull() {
            addCriterion("jersey_color is null");
            return (Criteria) this;
        }

        public Criteria andJerseyColorIsNotNull() {
            addCriterion("jersey_color is not null");
            return (Criteria) this;
        }

        public Criteria andJerseyColorEqualTo(String value) {
            addCriterion("jersey_color =", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorNotEqualTo(String value) {
            addCriterion("jersey_color <>", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorGreaterThan(String value) {
            addCriterion("jersey_color >", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorGreaterThanOrEqualTo(String value) {
            addCriterion("jersey_color >=", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorLessThan(String value) {
            addCriterion("jersey_color <", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorLessThanOrEqualTo(String value) {
            addCriterion("jersey_color <=", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorLike(String value) {
            addCriterion("jersey_color like", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorNotLike(String value) {
            addCriterion("jersey_color not like", value, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorIn(List<String> values) {
            addCriterion("jersey_color in", values, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorNotIn(List<String> values) {
            addCriterion("jersey_color not in", values, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorBetween(String value1, String value2) {
            addCriterion("jersey_color between", value1, value2, "jerseyColor");
            return (Criteria) this;
        }

        public Criteria andJerseyColorNotBetween(String value1, String value2) {
            addCriterion("jersey_color not between", value1, value2, "jerseyColor");
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