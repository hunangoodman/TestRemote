package com.treasure.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemberExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberExample() {
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

        public Criteria andFullNameIsNull() {
            addCriterion("full_name is null");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNotNull() {
            addCriterion("full_name is not null");
            return (Criteria) this;
        }

        public Criteria andFullNameEqualTo(String value) {
            addCriterion("full_name =", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotEqualTo(String value) {
            addCriterion("full_name <>", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThan(String value) {
            addCriterion("full_name >", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("full_name >=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThan(String value) {
            addCriterion("full_name <", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThanOrEqualTo(String value) {
            addCriterion("full_name <=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLike(String value) {
            addCriterion("full_name like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotLike(String value) {
            addCriterion("full_name not like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameIn(List<String> values) {
            addCriterion("full_name in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotIn(List<String> values) {
            addCriterion("full_name not in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameBetween(String value1, String value2) {
            addCriterion("full_name between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotBetween(String value1, String value2) {
            addCriterion("full_name not between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andAdCodeIsNull() {
            addCriterion("ad_code is null");
            return (Criteria) this;
        }

        public Criteria andAdCodeIsNotNull() {
            addCriterion("ad_code is not null");
            return (Criteria) this;
        }

        public Criteria andAdCodeEqualTo(String value) {
            addCriterion("ad_code =", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeNotEqualTo(String value) {
            addCriterion("ad_code <>", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeGreaterThan(String value) {
            addCriterion("ad_code >", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ad_code >=", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeLessThan(String value) {
            addCriterion("ad_code <", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeLessThanOrEqualTo(String value) {
            addCriterion("ad_code <=", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeLike(String value) {
            addCriterion("ad_code like", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeNotLike(String value) {
            addCriterion("ad_code not like", value, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeIn(List<String> values) {
            addCriterion("ad_code in", values, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeNotIn(List<String> values) {
            addCriterion("ad_code not in", values, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeBetween(String value1, String value2) {
            addCriterion("ad_code between", value1, value2, "adCode");
            return (Criteria) this;
        }

        public Criteria andAdCodeNotBetween(String value1, String value2) {
            addCriterion("ad_code not between", value1, value2, "adCode");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Double value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Double value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Double value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Double value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Double value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Double value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Double> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Double> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Double value1, Double value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Double value1, Double value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralIsNull() {
            addCriterion("freeze_integral is null");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralIsNotNull() {
            addCriterion("freeze_integral is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralEqualTo(Double value) {
            addCriterion("freeze_integral =", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralNotEqualTo(Double value) {
            addCriterion("freeze_integral <>", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralGreaterThan(Double value) {
            addCriterion("freeze_integral >", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralGreaterThanOrEqualTo(Double value) {
            addCriterion("freeze_integral >=", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralLessThan(Double value) {
            addCriterion("freeze_integral <", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralLessThanOrEqualTo(Double value) {
            addCriterion("freeze_integral <=", value, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralIn(List<Double> values) {
            addCriterion("freeze_integral in", values, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralNotIn(List<Double> values) {
            addCriterion("freeze_integral not in", values, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralBetween(Double value1, Double value2) {
            addCriterion("freeze_integral between", value1, value2, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andFreezeIntegralNotBetween(Double value1, Double value2) {
            addCriterion("freeze_integral not between", value1, value2, "freezeIntegral");
            return (Criteria) this;
        }

        public Criteria andSilverIsNull() {
            addCriterion("silver is null");
            return (Criteria) this;
        }

        public Criteria andSilverIsNotNull() {
            addCriterion("silver is not null");
            return (Criteria) this;
        }

        public Criteria andSilverEqualTo(Double value) {
            addCriterion("silver =", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotEqualTo(Double value) {
            addCriterion("silver <>", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverGreaterThan(Double value) {
            addCriterion("silver >", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverGreaterThanOrEqualTo(Double value) {
            addCriterion("silver >=", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverLessThan(Double value) {
            addCriterion("silver <", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverLessThanOrEqualTo(Double value) {
            addCriterion("silver <=", value, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverIn(List<Double> values) {
            addCriterion("silver in", values, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotIn(List<Double> values) {
            addCriterion("silver not in", values, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverBetween(Double value1, Double value2) {
            addCriterion("silver between", value1, value2, "silver");
            return (Criteria) this;
        }

        public Criteria andSilverNotBetween(Double value1, Double value2) {
            addCriterion("silver not between", value1, value2, "silver");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanIsNull() {
            addCriterion("consume_bean is null");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanIsNotNull() {
            addCriterion("consume_bean is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanEqualTo(Double value) {
            addCriterion("consume_bean =", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanNotEqualTo(Double value) {
            addCriterion("consume_bean <>", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanGreaterThan(Double value) {
            addCriterion("consume_bean >", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanGreaterThanOrEqualTo(Double value) {
            addCriterion("consume_bean >=", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanLessThan(Double value) {
            addCriterion("consume_bean <", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanLessThanOrEqualTo(Double value) {
            addCriterion("consume_bean <=", value, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanIn(List<Double> values) {
            addCriterion("consume_bean in", values, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanNotIn(List<Double> values) {
            addCriterion("consume_bean not in", values, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanBetween(Double value1, Double value2) {
            addCriterion("consume_bean between", value1, value2, "consumeBean");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanNotBetween(Double value1, Double value2) {
            addCriterion("consume_bean not between", value1, value2, "consumeBean");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusIsNull() {
            addCriterion("verified_status is null");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusIsNotNull() {
            addCriterion("verified_status is not null");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusEqualTo(Byte value) {
            addCriterion("verified_status =", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusNotEqualTo(Byte value) {
            addCriterion("verified_status <>", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusGreaterThan(Byte value) {
            addCriterion("verified_status >", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("verified_status >=", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusLessThan(Byte value) {
            addCriterion("verified_status <", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusLessThanOrEqualTo(Byte value) {
            addCriterion("verified_status <=", value, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusIn(List<Byte> values) {
            addCriterion("verified_status in", values, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusNotIn(List<Byte> values) {
            addCriterion("verified_status not in", values, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusBetween(Byte value1, Byte value2) {
            addCriterion("verified_status between", value1, value2, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andVerifiedStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("verified_status not between", value1, value2, "verifiedStatus");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andCardNumberIsNull() {
            addCriterion("card_number is null");
            return (Criteria) this;
        }

        public Criteria andCardNumberIsNotNull() {
            addCriterion("card_number is not null");
            return (Criteria) this;
        }

        public Criteria andCardNumberEqualTo(String value) {
            addCriterion("card_number =", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotEqualTo(String value) {
            addCriterion("card_number <>", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThan(String value) {
            addCriterion("card_number >", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("card_number >=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThan(String value) {
            addCriterion("card_number <", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLessThanOrEqualTo(String value) {
            addCriterion("card_number <=", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberLike(String value) {
            addCriterion("card_number like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotLike(String value) {
            addCriterion("card_number not like", value, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberIn(List<String> values) {
            addCriterion("card_number in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotIn(List<String> values) {
            addCriterion("card_number not in", values, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberBetween(String value1, String value2) {
            addCriterion("card_number between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andCardNumberNotBetween(String value1, String value2) {
            addCriterion("card_number not between", value1, value2, "cardNumber");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("id_card is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("id_card is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("id_card =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("id_card <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("id_card >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("id_card >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("id_card <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("id_card <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("id_card like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("id_card not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("id_card in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("id_card not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("id_card between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("id_card not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andHeadIsNull() {
            addCriterion("head is null");
            return (Criteria) this;
        }

        public Criteria andHeadIsNotNull() {
            addCriterion("head is not null");
            return (Criteria) this;
        }

        public Criteria andHeadEqualTo(String value) {
            addCriterion("head =", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotEqualTo(String value) {
            addCriterion("head <>", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThan(String value) {
            addCriterion("head >", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThanOrEqualTo(String value) {
            addCriterion("head >=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThan(String value) {
            addCriterion("head <", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThanOrEqualTo(String value) {
            addCriterion("head <=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLike(String value) {
            addCriterion("head like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotLike(String value) {
            addCriterion("head not like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadIn(List<String> values) {
            addCriterion("head in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotIn(List<String> values) {
            addCriterion("head not in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadBetween(String value1, String value2) {
            addCriterion("head between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotBetween(String value1, String value2) {
            addCriterion("head not between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoIsNull() {
            addCriterion("positive_photo is null");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoIsNotNull() {
            addCriterion("positive_photo is not null");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoEqualTo(String value) {
            addCriterion("positive_photo =", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoNotEqualTo(String value) {
            addCriterion("positive_photo <>", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoGreaterThan(String value) {
            addCriterion("positive_photo >", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoGreaterThanOrEqualTo(String value) {
            addCriterion("positive_photo >=", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoLessThan(String value) {
            addCriterion("positive_photo <", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoLessThanOrEqualTo(String value) {
            addCriterion("positive_photo <=", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoLike(String value) {
            addCriterion("positive_photo like", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoNotLike(String value) {
            addCriterion("positive_photo not like", value, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoIn(List<String> values) {
            addCriterion("positive_photo in", values, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoNotIn(List<String> values) {
            addCriterion("positive_photo not in", values, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoBetween(String value1, String value2) {
            addCriterion("positive_photo between", value1, value2, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andPositivePhotoNotBetween(String value1, String value2) {
            addCriterion("positive_photo not between", value1, value2, "positivePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoIsNull() {
            addCriterion("negative_photo is null");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoIsNotNull() {
            addCriterion("negative_photo is not null");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoEqualTo(String value) {
            addCriterion("negative_photo =", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoNotEqualTo(String value) {
            addCriterion("negative_photo <>", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoGreaterThan(String value) {
            addCriterion("negative_photo >", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoGreaterThanOrEqualTo(String value) {
            addCriterion("negative_photo >=", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoLessThan(String value) {
            addCriterion("negative_photo <", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoLessThanOrEqualTo(String value) {
            addCriterion("negative_photo <=", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoLike(String value) {
            addCriterion("negative_photo like", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoNotLike(String value) {
            addCriterion("negative_photo not like", value, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoIn(List<String> values) {
            addCriterion("negative_photo in", values, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoNotIn(List<String> values) {
            addCriterion("negative_photo not in", values, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoBetween(String value1, String value2) {
            addCriterion("negative_photo between", value1, value2, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andNegativePhotoNotBetween(String value1, String value2) {
            addCriterion("negative_photo not between", value1, value2, "negativePhoto");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andActivateStatusIsNull() {
            addCriterion("activate_status is null");
            return (Criteria) this;
        }

        public Criteria andActivateStatusIsNotNull() {
            addCriterion("activate_status is not null");
            return (Criteria) this;
        }

        public Criteria andActivateStatusEqualTo(Byte value) {
            addCriterion("activate_status =", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusNotEqualTo(Byte value) {
            addCriterion("activate_status <>", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusGreaterThan(Byte value) {
            addCriterion("activate_status >", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("activate_status >=", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusLessThan(Byte value) {
            addCriterion("activate_status <", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusLessThanOrEqualTo(Byte value) {
            addCriterion("activate_status <=", value, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusIn(List<Byte> values) {
            addCriterion("activate_status in", values, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusNotIn(List<Byte> values) {
            addCriterion("activate_status not in", values, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusBetween(Byte value1, Byte value2) {
            addCriterion("activate_status between", value1, value2, "activateStatus");
            return (Criteria) this;
        }

        public Criteria andActivateStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("activate_status not between", value1, value2, "activateStatus");
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

        public Criteria andThawDateIsNull() {
            addCriterion("thaw_date is null");
            return (Criteria) this;
        }

        public Criteria andThawDateIsNotNull() {
            addCriterion("thaw_date is not null");
            return (Criteria) this;
        }

        public Criteria andThawDateEqualTo(Date value) {
            addCriterionForJDBCDate("thaw_date =", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("thaw_date <>", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateGreaterThan(Date value) {
            addCriterionForJDBCDate("thaw_date >", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("thaw_date >=", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateLessThan(Date value) {
            addCriterionForJDBCDate("thaw_date <", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("thaw_date <=", value, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateIn(List<Date> values) {
            addCriterionForJDBCDate("thaw_date in", values, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("thaw_date not in", values, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("thaw_date between", value1, value2, "thawDate");
            return (Criteria) this;
        }

        public Criteria andThawDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("thaw_date not between", value1, value2, "thawDate");
            return (Criteria) this;
        }

        public Criteria andBankIsNull() {
            addCriterion("bank is null");
            return (Criteria) this;
        }

        public Criteria andBankIsNotNull() {
            addCriterion("bank is not null");
            return (Criteria) this;
        }

        public Criteria andBankEqualTo(String value) {
            addCriterion("bank =", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotEqualTo(String value) {
            addCriterion("bank <>", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThan(String value) {
            addCriterion("bank >", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankGreaterThanOrEqualTo(String value) {
            addCriterion("bank >=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThan(String value) {
            addCriterion("bank <", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLessThanOrEqualTo(String value) {
            addCriterion("bank <=", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankLike(String value) {
            addCriterion("bank like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotLike(String value) {
            addCriterion("bank not like", value, "bank");
            return (Criteria) this;
        }

        public Criteria andBankIn(List<String> values) {
            addCriterion("bank in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotIn(List<String> values) {
            addCriterion("bank not in", values, "bank");
            return (Criteria) this;
        }

        public Criteria andBankBetween(String value1, String value2) {
            addCriterion("bank between", value1, value2, "bank");
            return (Criteria) this;
        }

        public Criteria andBankNotBetween(String value1, String value2) {
            addCriterion("bank not between", value1, value2, "bank");
            return (Criteria) this;
        }
        
        public Criteria andPasswordTwoIsNull() {
            addCriterion("password_two is null");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoIsNotNull() {
            addCriterion("password_two is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoEqualTo(String value) {
            addCriterion("password_two =", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoNotEqualTo(String value) {
            addCriterion("password_two <>", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoGreaterThan(String value) {
            addCriterion("password_two >", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoGreaterThanOrEqualTo(String value) {
            addCriterion("password_two >=", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoLessThan(String value) {
            addCriterion("password_two <", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoLessThanOrEqualTo(String value) {
            addCriterion("password_two <=", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoLike(String value) {
            addCriterion("password_two like", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoNotLike(String value) {
            addCriterion("password_two not like", value, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoIn(List<String> values) {
            addCriterion("password_two in", values, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoNotIn(List<String> values) {
            addCriterion("password_two not in", values, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoBetween(String value1, String value2) {
            addCriterion("password_two between", value1, value2, "passwordTwo");
            return (Criteria) this;
        }

        public Criteria andPasswordTwoNotBetween(String value1, String value2) {
            addCriterion("password_two not between", value1, value2, "passwordTwo");
            return (Criteria) this;
        }
        
        public Criteria andConnectionIsNull() {
            addCriterion("connection is null");
            return (Criteria) this;
        }

        public Criteria andConnectionIsNotNull() {
            addCriterion("connection is not null");
            return (Criteria) this;
        }

        public Criteria andConnectionEqualTo(String value) {
            addCriterion("connection =", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionNotEqualTo(String value) {
            addCriterion("connection <>", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionGreaterThan(String value) {
            addCriterion("connection >", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionGreaterThanOrEqualTo(String value) {
            addCriterion("connection >=", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionLessThan(String value) {
            addCriterion("connection <", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionLessThanOrEqualTo(String value) {
            addCriterion("connection <=", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionLike(String value) {
            addCriterion("connection like", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionNotLike(String value) {
            addCriterion("connection not like", value, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionIn(List<String> values) {
            addCriterion("connection in", values, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionNotIn(List<String> values) {
            addCriterion("connection not in", values, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionBetween(String value1, String value2) {
            addCriterion("connection between", value1, value2, "connection");
            return (Criteria) this;
        }

        public Criteria andConnectionNotBetween(String value1, String value2) {
            addCriterion("connection not between", value1, value2, "connection");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyIsNull() {
            addCriterion("recommend_money is null");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyIsNotNull() {
            addCriterion("recommend_money is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyEqualTo(Double value) {
            addCriterion("recommend_money =", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyNotEqualTo(Double value) {
            addCriterion("recommend_money <>", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyGreaterThan(Double value) {
            addCriterion("recommend_money >", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("recommend_money >=", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyLessThan(Double value) {
            addCriterion("recommend_money <", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyLessThanOrEqualTo(Double value) {
            addCriterion("recommend_money <=", value, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyIn(List<Double> values) {
            addCriterion("recommend_money in", values, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyNotIn(List<Double> values) {
            addCriterion("recommend_money not in", values, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyBetween(Double value1, Double value2) {
            addCriterion("recommend_money between", value1, value2, "recommendMoney");
            return (Criteria) this;
        }

        public Criteria andRecommendMoneyNotBetween(Double value1, Double value2) {
            addCriterion("recommend_money not between", value1, value2, "recommendMoney");
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