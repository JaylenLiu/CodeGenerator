package cn.jaylen.codegenerator.entity.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgileEntityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AgileEntityExample() {
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

        public Criteria andSchemaIdIsNull() {
            addCriterion("schema_id is null");
            return (Criteria) this;
        }

        public Criteria andSchemaIdIsNotNull() {
            addCriterion("schema_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchemaIdEqualTo(Long value) {
            addCriterion("schema_id =", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdNotEqualTo(Long value) {
            addCriterion("schema_id <>", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdGreaterThan(Long value) {
            addCriterion("schema_id >", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdGreaterThanOrEqualTo(Long value) {
            addCriterion("schema_id >=", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdLessThan(Long value) {
            addCriterion("schema_id <", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdLessThanOrEqualTo(Long value) {
            addCriterion("schema_id <=", value, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdIn(List<Long> values) {
            addCriterion("schema_id in", values, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdNotIn(List<Long> values) {
            addCriterion("schema_id not in", values, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdBetween(Long value1, Long value2) {
            addCriterion("schema_id between", value1, value2, "schemaId");
            return (Criteria) this;
        }

        public Criteria andSchemaIdNotBetween(Long value1, Long value2) {
            addCriterion("schema_id not between", value1, value2, "schemaId");
            return (Criteria) this;
        }

        public Criteria andConIdIsNull() {
            addCriterion("con_id is null");
            return (Criteria) this;
        }

        public Criteria andConIdIsNotNull() {
            addCriterion("con_id is not null");
            return (Criteria) this;
        }

        public Criteria andConIdEqualTo(Long value) {
            addCriterion("con_id =", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdNotEqualTo(Long value) {
            addCriterion("con_id <>", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdGreaterThan(Long value) {
            addCriterion("con_id >", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdGreaterThanOrEqualTo(Long value) {
            addCriterion("con_id >=", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdLessThan(Long value) {
            addCriterion("con_id <", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdLessThanOrEqualTo(Long value) {
            addCriterion("con_id <=", value, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdIn(List<Long> values) {
            addCriterion("con_id in", values, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdNotIn(List<Long> values) {
            addCriterion("con_id not in", values, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdBetween(Long value1, Long value2) {
            addCriterion("con_id between", value1, value2, "conId");
            return (Criteria) this;
        }

        public Criteria andConIdNotBetween(Long value1, Long value2) {
            addCriterion("con_id not between", value1, value2, "conId");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNull() {
            addCriterion("database_name is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIsNotNull() {
            addCriterion("database_name is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameEqualTo(String value) {
            addCriterion("database_name =", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotEqualTo(String value) {
            addCriterion("database_name <>", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThan(String value) {
            addCriterion("database_name >", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("database_name >=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThan(String value) {
            addCriterion("database_name <", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLessThanOrEqualTo(String value) {
            addCriterion("database_name <=", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameLike(String value) {
            addCriterion("database_name like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotLike(String value) {
            addCriterion("database_name not like", value, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameIn(List<String> values) {
            addCriterion("database_name in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotIn(List<String> values) {
            addCriterion("database_name not in", values, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameBetween(String value1, String value2) {
            addCriterion("database_name between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andDatabaseNameNotBetween(String value1, String value2) {
            addCriterion("database_name not between", value1, value2, "databaseName");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdIsNull() {
            addCriterion("parent_entity_id is null");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdIsNotNull() {
            addCriterion("parent_entity_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdEqualTo(Long value) {
            addCriterion("parent_entity_id =", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdNotEqualTo(Long value) {
            addCriterion("parent_entity_id <>", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdGreaterThan(Long value) {
            addCriterion("parent_entity_id >", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_entity_id >=", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdLessThan(Long value) {
            addCriterion("parent_entity_id <", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_entity_id <=", value, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdIn(List<Long> values) {
            addCriterion("parent_entity_id in", values, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdNotIn(List<Long> values) {
            addCriterion("parent_entity_id not in", values, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdBetween(Long value1, Long value2) {
            addCriterion("parent_entity_id between", value1, value2, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andParentEntityIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_entity_id not between", value1, value2, "parentEntityId");
            return (Criteria) this;
        }

        public Criteria andEntityTypeIsNull() {
            addCriterion("entity_type is null");
            return (Criteria) this;
        }

        public Criteria andEntityTypeIsNotNull() {
            addCriterion("entity_type is not null");
            return (Criteria) this;
        }

        public Criteria andEntityTypeEqualTo(Integer value) {
            addCriterion("entity_type =", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotEqualTo(Integer value) {
            addCriterion("entity_type <>", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeGreaterThan(Integer value) {
            addCriterion("entity_type >", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("entity_type >=", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeLessThan(Integer value) {
            addCriterion("entity_type <", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeLessThanOrEqualTo(Integer value) {
            addCriterion("entity_type <=", value, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeIn(List<Integer> values) {
            addCriterion("entity_type in", values, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotIn(List<Integer> values) {
            addCriterion("entity_type not in", values, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeBetween(Integer value1, Integer value2) {
            addCriterion("entity_type between", value1, value2, "entityType");
            return (Criteria) this;
        }

        public Criteria andEntityTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("entity_type not between", value1, value2, "entityType");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("class_name is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("class_name is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("class_name =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("class_name <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("class_name >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("class_name >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("class_name <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("class_name <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("class_name like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("class_name not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("class_name in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("class_name not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("class_name between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("class_name not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andParentFieldIsNull() {
            addCriterion("parent_field is null");
            return (Criteria) this;
        }

        public Criteria andParentFieldIsNotNull() {
            addCriterion("parent_field is not null");
            return (Criteria) this;
        }

        public Criteria andParentFieldEqualTo(String value) {
            addCriterion("parent_field =", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldNotEqualTo(String value) {
            addCriterion("parent_field <>", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldGreaterThan(String value) {
            addCriterion("parent_field >", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldGreaterThanOrEqualTo(String value) {
            addCriterion("parent_field >=", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldLessThan(String value) {
            addCriterion("parent_field <", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldLessThanOrEqualTo(String value) {
            addCriterion("parent_field <=", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldLike(String value) {
            addCriterion("parent_field like", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldNotLike(String value) {
            addCriterion("parent_field not like", value, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldIn(List<String> values) {
            addCriterion("parent_field in", values, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldNotIn(List<String> values) {
            addCriterion("parent_field not in", values, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldBetween(String value1, String value2) {
            addCriterion("parent_field between", value1, value2, "parentField");
            return (Criteria) this;
        }

        public Criteria andParentFieldNotBetween(String value1, String value2) {
            addCriterion("parent_field not between", value1, value2, "parentField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldIsNull() {
            addCriterion("relation_field is null");
            return (Criteria) this;
        }

        public Criteria andRelationFieldIsNotNull() {
            addCriterion("relation_field is not null");
            return (Criteria) this;
        }

        public Criteria andRelationFieldEqualTo(String value) {
            addCriterion("relation_field =", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldNotEqualTo(String value) {
            addCriterion("relation_field <>", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldGreaterThan(String value) {
            addCriterion("relation_field >", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldGreaterThanOrEqualTo(String value) {
            addCriterion("relation_field >=", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldLessThan(String value) {
            addCriterion("relation_field <", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldLessThanOrEqualTo(String value) {
            addCriterion("relation_field <=", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldLike(String value) {
            addCriterion("relation_field like", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldNotLike(String value) {
            addCriterion("relation_field not like", value, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldIn(List<String> values) {
            addCriterion("relation_field in", values, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldNotIn(List<String> values) {
            addCriterion("relation_field not in", values, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldBetween(String value1, String value2) {
            addCriterion("relation_field between", value1, value2, "relationField");
            return (Criteria) this;
        }

        public Criteria andRelationFieldNotBetween(String value1, String value2) {
            addCriterion("relation_field not between", value1, value2, "relationField");
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