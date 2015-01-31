package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CourtSiteSectionDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow = -1;

    protected int pageSize = -1;

    public CourtSiteSectionDOCriteria() {
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

        public Criteria andCourtSiteIdIsNull() {
            addCriterion("court_site_id is null");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdIsNotNull() {
            addCriterion("court_site_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdEqualTo(Long value) {
            addCriterion("court_site_id =", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdNotEqualTo(Long value) {
            addCriterion("court_site_id <>", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdGreaterThan(Long value) {
            addCriterion("court_site_id >", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdGreaterThanOrEqualTo(Long value) {
            addCriterion("court_site_id >=", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdLessThan(Long value) {
            addCriterion("court_site_id <", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdLessThanOrEqualTo(Long value) {
            addCriterion("court_site_id <=", value, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdIn(List<Long> values) {
            addCriterion("court_site_id in", values, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdNotIn(List<Long> values) {
            addCriterion("court_site_id not in", values, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdBetween(Long value1, Long value2) {
            addCriterion("court_site_id between", value1, value2, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andCourtSiteIdNotBetween(Long value1, Long value2) {
            addCriterion("court_site_id not between", value1, value2, "courtSiteId");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNull() {
            addCriterion("date_info is null");
            return (Criteria) this;
        }

        public Criteria andDateInfoIsNotNull() {
            addCriterion("date_info is not null");
            return (Criteria) this;
        }

        public Criteria andDateInfoEqualTo(Date value) {
            addCriterionForJDBCDate("date_info =", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotEqualTo(Date value) {
            addCriterionForJDBCDate("date_info <>", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThan(Date value) {
            addCriterionForJDBCDate("date_info >", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_info >=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThan(Date value) {
            addCriterionForJDBCDate("date_info <", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date_info <=", value, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoIn(List<Date> values) {
            addCriterionForJDBCDate("date_info in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotIn(List<Date> values) {
            addCriterionForJDBCDate("date_info not in", values, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_info between", value1, value2, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andDateInfoNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date_info not between", value1, value2, "dateInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoIsNull() {
            addCriterion("time_info is null");
            return (Criteria) this;
        }

        public Criteria andTimeInfoIsNotNull() {
            addCriterion("time_info is not null");
            return (Criteria) this;
        }

        public Criteria andTimeInfoEqualTo(String value) {
            addCriterion("time_info =", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoNotEqualTo(String value) {
            addCriterion("time_info <>", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoGreaterThan(String value) {
            addCriterion("time_info >", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoGreaterThanOrEqualTo(String value) {
            addCriterion("time_info >=", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoLessThan(String value) {
            addCriterion("time_info <", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoLessThanOrEqualTo(String value) {
            addCriterion("time_info <=", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoLike(String value) {
            addCriterion("time_info like", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoNotLike(String value) {
            addCriterion("time_info not like", value, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoIn(List<String> values) {
            addCriterion("time_info in", values, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoNotIn(List<String> values) {
            addCriterion("time_info not in", values, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoBetween(String value1, String value2) {
            addCriterion("time_info between", value1, value2, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andTimeInfoNotBetween(String value1, String value2) {
            addCriterion("time_info not between", value1, value2, "timeInfo");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Long value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Long value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Long value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Long value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Long value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Long value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Long> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Long> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Long value1, Long value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Long value1, Long value2) {
            addCriterion("price not between", value1, value2, "price");
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