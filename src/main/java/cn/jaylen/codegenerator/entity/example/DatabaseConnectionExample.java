package cn.jaylen.codegenerator.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseConnectionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatabaseConnectionExample() {
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

        public Criteria andConnNameIsNull() {
            addCriterion("conn_name is null");
            return (Criteria) this;
        }

        public Criteria andConnNameIsNotNull() {
            addCriterion("conn_name is not null");
            return (Criteria) this;
        }

        public Criteria andConnNameEqualTo(String value) {
            addCriterion("conn_name =", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameNotEqualTo(String value) {
            addCriterion("conn_name <>", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameGreaterThan(String value) {
            addCriterion("conn_name >", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameGreaterThanOrEqualTo(String value) {
            addCriterion("conn_name >=", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameLessThan(String value) {
            addCriterion("conn_name <", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameLessThanOrEqualTo(String value) {
            addCriterion("conn_name <=", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameLike(String value) {
            addCriterion("conn_name like", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameNotLike(String value) {
            addCriterion("conn_name not like", value, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameIn(List<String> values) {
            addCriterion("conn_name in", values, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameNotIn(List<String> values) {
            addCriterion("conn_name not in", values, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameBetween(String value1, String value2) {
            addCriterion("conn_name between", value1, value2, "connName");
            return (Criteria) this;
        }

        public Criteria andConnNameNotBetween(String value1, String value2) {
            addCriterion("conn_name not between", value1, value2, "connName");
            return (Criteria) this;
        }

        public Criteria andConnTypeIsNull() {
            addCriterion("conn_type is null");
            return (Criteria) this;
        }

        public Criteria andConnTypeIsNotNull() {
            addCriterion("conn_type is not null");
            return (Criteria) this;
        }

        public Criteria andConnTypeEqualTo(Integer value) {
            addCriterion("conn_type =", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotEqualTo(Integer value) {
            addCriterion("conn_type <>", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeGreaterThan(Integer value) {
            addCriterion("conn_type >", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("conn_type >=", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeLessThan(Integer value) {
            addCriterion("conn_type <", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeLessThanOrEqualTo(Integer value) {
            addCriterion("conn_type <=", value, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeIn(List<Integer> values) {
            addCriterion("conn_type in", values, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotIn(List<Integer> values) {
            addCriterion("conn_type not in", values, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeBetween(Integer value1, Integer value2) {
            addCriterion("conn_type between", value1, value2, "connType");
            return (Criteria) this;
        }

        public Criteria andConnTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("conn_type not between", value1, value2, "connType");
            return (Criteria) this;
        }

        public Criteria andConnIpIsNull() {
            addCriterion("conn_ip is null");
            return (Criteria) this;
        }

        public Criteria andConnIpIsNotNull() {
            addCriterion("conn_ip is not null");
            return (Criteria) this;
        }

        public Criteria andConnIpEqualTo(String value) {
            addCriterion("conn_ip =", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpNotEqualTo(String value) {
            addCriterion("conn_ip <>", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpGreaterThan(String value) {
            addCriterion("conn_ip >", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpGreaterThanOrEqualTo(String value) {
            addCriterion("conn_ip >=", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpLessThan(String value) {
            addCriterion("conn_ip <", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpLessThanOrEqualTo(String value) {
            addCriterion("conn_ip <=", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpLike(String value) {
            addCriterion("conn_ip like", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpNotLike(String value) {
            addCriterion("conn_ip not like", value, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpIn(List<String> values) {
            addCriterion("conn_ip in", values, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpNotIn(List<String> values) {
            addCriterion("conn_ip not in", values, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpBetween(String value1, String value2) {
            addCriterion("conn_ip between", value1, value2, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnIpNotBetween(String value1, String value2) {
            addCriterion("conn_ip not between", value1, value2, "connIp");
            return (Criteria) this;
        }

        public Criteria andConnPortIsNull() {
            addCriterion("conn_port is null");
            return (Criteria) this;
        }

        public Criteria andConnPortIsNotNull() {
            addCriterion("conn_port is not null");
            return (Criteria) this;
        }

        public Criteria andConnPortEqualTo(Integer value) {
            addCriterion("conn_port =", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortNotEqualTo(Integer value) {
            addCriterion("conn_port <>", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortGreaterThan(Integer value) {
            addCriterion("conn_port >", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortGreaterThanOrEqualTo(Integer value) {
            addCriterion("conn_port >=", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortLessThan(Integer value) {
            addCriterion("conn_port <", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortLessThanOrEqualTo(Integer value) {
            addCriterion("conn_port <=", value, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortIn(List<Integer> values) {
            addCriterion("conn_port in", values, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortNotIn(List<Integer> values) {
            addCriterion("conn_port not in", values, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortBetween(Integer value1, Integer value2) {
            addCriterion("conn_port between", value1, value2, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnPortNotBetween(Integer value1, Integer value2) {
            addCriterion("conn_port not between", value1, value2, "connPort");
            return (Criteria) this;
        }

        public Criteria andConnUsernameIsNull() {
            addCriterion("conn_username is null");
            return (Criteria) this;
        }

        public Criteria andConnUsernameIsNotNull() {
            addCriterion("conn_username is not null");
            return (Criteria) this;
        }

        public Criteria andConnUsernameEqualTo(String value) {
            addCriterion("conn_username =", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameNotEqualTo(String value) {
            addCriterion("conn_username <>", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameGreaterThan(String value) {
            addCriterion("conn_username >", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("conn_username >=", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameLessThan(String value) {
            addCriterion("conn_username <", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameLessThanOrEqualTo(String value) {
            addCriterion("conn_username <=", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameLike(String value) {
            addCriterion("conn_username like", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameNotLike(String value) {
            addCriterion("conn_username not like", value, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameIn(List<String> values) {
            addCriterion("conn_username in", values, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameNotIn(List<String> values) {
            addCriterion("conn_username not in", values, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameBetween(String value1, String value2) {
            addCriterion("conn_username between", value1, value2, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnUsernameNotBetween(String value1, String value2) {
            addCriterion("conn_username not between", value1, value2, "connUsername");
            return (Criteria) this;
        }

        public Criteria andConnPwdIsNull() {
            addCriterion("conn_pwd is null");
            return (Criteria) this;
        }

        public Criteria andConnPwdIsNotNull() {
            addCriterion("conn_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andConnPwdEqualTo(String value) {
            addCriterion("conn_pwd =", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdNotEqualTo(String value) {
            addCriterion("conn_pwd <>", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdGreaterThan(String value) {
            addCriterion("conn_pwd >", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdGreaterThanOrEqualTo(String value) {
            addCriterion("conn_pwd >=", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdLessThan(String value) {
            addCriterion("conn_pwd <", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdLessThanOrEqualTo(String value) {
            addCriterion("conn_pwd <=", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdLike(String value) {
            addCriterion("conn_pwd like", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdNotLike(String value) {
            addCriterion("conn_pwd not like", value, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdIn(List<String> values) {
            addCriterion("conn_pwd in", values, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdNotIn(List<String> values) {
            addCriterion("conn_pwd not in", values, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdBetween(String value1, String value2) {
            addCriterion("conn_pwd between", value1, value2, "connPwd");
            return (Criteria) this;
        }

        public Criteria andConnPwdNotBetween(String value1, String value2) {
            addCriterion("conn_pwd not between", value1, value2, "connPwd");
            return (Criteria) this;
        }

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(String value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(String value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(String value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(String value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(String value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(String value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLike(String value) {
            addCriterion("sid like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotLike(String value) {
            addCriterion("sid not like", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<String> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<String> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(String value1, String value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(String value1, String value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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