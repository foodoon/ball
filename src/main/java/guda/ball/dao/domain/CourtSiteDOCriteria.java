package guda.ball.dao.domain;

import java.util.ArrayList;
import java.util.List;

public class CourtSiteDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int startRow = -1;

    protected int pageSize = -1;

    public CourtSiteDOCriteria() {
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

        public Criteria andCourtIdIsNull() {
            addCriterion("court_id is null");
            return (Criteria) this;
        }

        public Criteria andCourtIdIsNotNull() {
            addCriterion("court_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourtIdEqualTo(Long value) {
            addCriterion("court_id =", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotEqualTo(Long value) {
            addCriterion("court_id <>", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdGreaterThan(Long value) {
            addCriterion("court_id >", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdGreaterThanOrEqualTo(Long value) {
            addCriterion("court_id >=", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdLessThan(Long value) {
            addCriterion("court_id <", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdLessThanOrEqualTo(Long value) {
            addCriterion("court_id <=", value, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdIn(List<Long> values) {
            addCriterion("court_id in", values, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotIn(List<Long> values) {
            addCriterion("court_id not in", values, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdBetween(Long value1, Long value2) {
            addCriterion("court_id between", value1, value2, "courtId");
            return (Criteria) this;
        }

        public Criteria andCourtIdNotBetween(Long value1, Long value2) {
            addCriterion("court_id not between", value1, value2, "courtId");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNull() {
            addCriterion("site_name is null");
            return (Criteria) this;
        }

        public Criteria andSiteNameIsNotNull() {
            addCriterion("site_name is not null");
            return (Criteria) this;
        }

        public Criteria andSiteNameEqualTo(String value) {
            addCriterion("site_name =", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotEqualTo(String value) {
            addCriterion("site_name <>", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThan(String value) {
            addCriterion("site_name >", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameGreaterThanOrEqualTo(String value) {
            addCriterion("site_name >=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThan(String value) {
            addCriterion("site_name <", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLessThanOrEqualTo(String value) {
            addCriterion("site_name <=", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameLike(String value) {
            addCriterion("site_name like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotLike(String value) {
            addCriterion("site_name not like", value, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameIn(List<String> values) {
            addCriterion("site_name in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotIn(List<String> values) {
            addCriterion("site_name not in", values, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameBetween(String value1, String value2) {
            addCriterion("site_name between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteNameNotBetween(String value1, String value2) {
            addCriterion("site_name not between", value1, value2, "siteName");
            return (Criteria) this;
        }

        public Criteria andSiteTypeIsNull() {
            addCriterion("site_type is null");
            return (Criteria) this;
        }

        public Criteria andSiteTypeIsNotNull() {
            addCriterion("site_type is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTypeEqualTo(String value) {
            addCriterion("site_type =", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotEqualTo(String value) {
            addCriterion("site_type <>", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeGreaterThan(String value) {
            addCriterion("site_type >", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("site_type >=", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLessThan(String value) {
            addCriterion("site_type <", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLessThanOrEqualTo(String value) {
            addCriterion("site_type <=", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLike(String value) {
            addCriterion("site_type like", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotLike(String value) {
            addCriterion("site_type not like", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeIn(List<String> values) {
            addCriterion("site_type in", values, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotIn(List<String> values) {
            addCriterion("site_type not in", values, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeBetween(String value1, String value2) {
            addCriterion("site_type between", value1, value2, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotBetween(String value1, String value2) {
            addCriterion("site_type not between", value1, value2, "siteType");
            return (Criteria) this;
        }

        public Criteria andOpenIsNull() {
            addCriterion("open is null");
            return (Criteria) this;
        }

        public Criteria andOpenIsNotNull() {
            addCriterion("open is not null");
            return (Criteria) this;
        }

        public Criteria andOpenEqualTo(Integer value) {
            addCriterion("open =", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotEqualTo(Integer value) {
            addCriterion("open <>", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThan(Integer value) {
            addCriterion("open >", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenGreaterThanOrEqualTo(Integer value) {
            addCriterion("open >=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThan(Integer value) {
            addCriterion("open <", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenLessThanOrEqualTo(Integer value) {
            addCriterion("open <=", value, "open");
            return (Criteria) this;
        }

        public Criteria andOpenIn(List<Integer> values) {
            addCriterion("open in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotIn(List<Integer> values) {
            addCriterion("open not in", values, "open");
            return (Criteria) this;
        }

        public Criteria andOpenBetween(Integer value1, Integer value2) {
            addCriterion("open between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenNotBetween(Integer value1, Integer value2) {
            addCriterion("open not between", value1, value2, "open");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateIsNull() {
            addCriterion("open_template is null");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateIsNotNull() {
            addCriterion("open_template is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateEqualTo(String value) {
            addCriterion("open_template =", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateNotEqualTo(String value) {
            addCriterion("open_template <>", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateGreaterThan(String value) {
            addCriterion("open_template >", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("open_template >=", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateLessThan(String value) {
            addCriterion("open_template <", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateLessThanOrEqualTo(String value) {
            addCriterion("open_template <=", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateLike(String value) {
            addCriterion("open_template like", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateNotLike(String value) {
            addCriterion("open_template not like", value, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateIn(List<String> values) {
            addCriterion("open_template in", values, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateNotIn(List<String> values) {
            addCriterion("open_template not in", values, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateBetween(String value1, String value2) {
            addCriterion("open_template between", value1, value2, "openTemplate");
            return (Criteria) this;
        }

        public Criteria andOpenTemplateNotBetween(String value1, String value2) {
            addCriterion("open_template not between", value1, value2, "openTemplate");
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