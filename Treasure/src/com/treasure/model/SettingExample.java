package com.treasure.model;

import java.util.ArrayList;
import java.util.List;

public class SettingExample {
    protected String orderByClause;

    protected boolean distinct;
                 
    protected List<Criteria> oredCriteria;
    
    
    public SettingExample() {
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

        public Criteria andTotalCountIsNull() {
            addCriterion("total_count is null");
            return (Criteria) this;
        }

        public Criteria andTotalCountIsNotNull() {
            addCriterion("total_count is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCountEqualTo(Double value) {
            addCriterion("total_count =", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotEqualTo(Double value) {
            addCriterion("total_count <>", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThan(Double value) {
            addCriterion("total_count >", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountGreaterThanOrEqualTo(Double value) {
            addCriterion("total_count >=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThan(Double value) {
            addCriterion("total_count <", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountLessThanOrEqualTo(Double value) {
            addCriterion("total_count <=", value, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountIn(List<Double> values) {
            addCriterion("total_count in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotIn(List<Double> values) {
            addCriterion("total_count not in", values, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountBetween(Double value1, Double value2) {
            addCriterion("total_count between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andTotalCountNotBetween(Double value1, Double value2) {
            addCriterion("total_count not between", value1, value2, "totalCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountIsNull() {
            addCriterion("used_count is null");
            return (Criteria) this;
        }

        public Criteria andUsedCountIsNotNull() {
            addCriterion("used_count is not null");
            return (Criteria) this;
        }

        public Criteria andUsedCountEqualTo(Double value) {
            addCriterion("used_count =", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountNotEqualTo(Double value) {
            addCriterion("used_count <>", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountGreaterThan(Double value) {
            addCriterion("used_count >", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountGreaterThanOrEqualTo(Double value) {
            addCriterion("used_count >=", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountLessThan(Double value) {
            addCriterion("used_count <", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountLessThanOrEqualTo(Double value) {
            addCriterion("used_count <=", value, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountIn(List<Double> values) {
            addCriterion("used_count in", values, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountNotIn(List<Double> values) {
            addCriterion("used_count not in", values, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountBetween(Double value1, Double value2) {
            addCriterion("used_count between", value1, value2, "usedCount");
            return (Criteria) this;
        }

        public Criteria andUsedCountNotBetween(Double value1, Double value2) {
            addCriterion("used_count not between", value1, value2, "usedCount");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIsNull() {
            addCriterion("integral_price is null");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIsNotNull() {
            addCriterion("integral_price is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceEqualTo(Double value) {
            addCriterion("integral_price =", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotEqualTo(Double value) {
            addCriterion("integral_price <>", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceGreaterThan(Double value) {
            addCriterion("integral_price >", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("integral_price >=", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceLessThan(Double value) {
            addCriterion("integral_price <", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceLessThanOrEqualTo(Double value) {
            addCriterion("integral_price <=", value, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceIn(List<Double> values) {
            addCriterion("integral_price in", values, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotIn(List<Double> values) {
            addCriterion("integral_price not in", values, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceBetween(Double value1, Double value2) {
            addCriterion("integral_price between", value1, value2, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andIntegralPriceNotBetween(Double value1, Double value2) {
            addCriterion("integral_price not between", value1, value2, "integralPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceIsNull() {
            addCriterion("silver_price is null");
            return (Criteria) this;
        }

        public Criteria andSilverPriceIsNotNull() {
            addCriterion("silver_price is not null");
            return (Criteria) this;
        }

        public Criteria andSilverPriceEqualTo(Double value) {
            addCriterion("silver_price =", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceNotEqualTo(Double value) {
            addCriterion("silver_price <>", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceGreaterThan(Double value) {
            addCriterion("silver_price >", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("silver_price >=", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceLessThan(Double value) {
            addCriterion("silver_price <", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceLessThanOrEqualTo(Double value) {
            addCriterion("silver_price <=", value, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceIn(List<Double> values) {
            addCriterion("silver_price in", values, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceNotIn(List<Double> values) {
            addCriterion("silver_price not in", values, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceBetween(Double value1, Double value2) {
            addCriterion("silver_price between", value1, value2, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andSilverPriceNotBetween(Double value1, Double value2) {
            addCriterion("silver_price not between", value1, value2, "silverPrice");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNull() {
            addCriterion("amplitude is null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIsNotNull() {
            addCriterion("amplitude is not null");
            return (Criteria) this;
        }

        public Criteria andAmplitudeEqualTo(Double value) {
            addCriterion("amplitude =", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotEqualTo(Double value) {
            addCriterion("amplitude <>", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThan(Double value) {
            addCriterion("amplitude >", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("amplitude >=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThan(Double value) {
            addCriterion("amplitude <", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeLessThanOrEqualTo(Double value) {
            addCriterion("amplitude <=", value, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeIn(List<Double> values) {
            addCriterion("amplitude in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotIn(List<Double> values) {
            addCriterion("amplitude not in", values, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeBetween(Double value1, Double value2) {
            addCriterion("amplitude between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andAmplitudeNotBetween(Double value1, Double value2) {
            addCriterion("amplitude not between", value1, value2, "amplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeIsNull() {
            addCriterion("count_amplitude is null");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeIsNotNull() {
            addCriterion("count_amplitude is not null");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeEqualTo(Double value) {
            addCriterion("count_amplitude =", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeNotEqualTo(Double value) {
            addCriterion("count_amplitude <>", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeGreaterThan(Double value) {
            addCriterion("count_amplitude >", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeGreaterThanOrEqualTo(Double value) {
            addCriterion("count_amplitude >=", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeLessThan(Double value) {
            addCriterion("count_amplitude <", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeLessThanOrEqualTo(Double value) {
            addCriterion("count_amplitude <=", value, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeIn(List<Double> values) {
            addCriterion("count_amplitude in", values, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeNotIn(List<Double> values) {
            addCriterion("count_amplitude not in", values, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeBetween(Double value1, Double value2) {
            addCriterion("count_amplitude between", value1, value2, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andCountAmplitudeNotBetween(Double value1, Double value2) {
            addCriterion("count_amplitude not between", value1, value2, "countAmplitude");
            return (Criteria) this;
        }

        public Criteria andUpOrDownIsNull() {
            addCriterion("up_or_down is null");
            return (Criteria) this;
        }

        public Criteria andUpOrDownIsNotNull() {
            addCriterion("up_or_down is not null");
            return (Criteria) this;
        }

        public Criteria andUpOrDownEqualTo(Boolean value) {
            addCriterion("up_or_down =", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownNotEqualTo(Boolean value) {
            addCriterion("up_or_down <>", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownGreaterThan(Boolean value) {
            addCriterion("up_or_down >", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownGreaterThanOrEqualTo(Boolean value) {
            addCriterion("up_or_down >=", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownLessThan(Boolean value) {
            addCriterion("up_or_down <", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownLessThanOrEqualTo(Boolean value) {
            addCriterion("up_or_down <=", value, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownIn(List<Boolean> values) {
            addCriterion("up_or_down in", values, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownNotIn(List<Boolean> values) {
            addCriterion("up_or_down not in", values, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownBetween(Boolean value1, Boolean value2) {
            addCriterion("up_or_down between", value1, value2, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andUpOrDownNotBetween(Boolean value1, Boolean value2) {
            addCriterion("up_or_down not between", value1, value2, "upOrDown");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestIsNull() {
            addCriterion("history_highest is null");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestIsNotNull() {
            addCriterion("history_highest is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestEqualTo(Double value) {
            addCriterion("history_highest =", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestNotEqualTo(Double value) {
            addCriterion("history_highest <>", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestGreaterThan(Double value) {
            addCriterion("history_highest >", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestGreaterThanOrEqualTo(Double value) {
            addCriterion("history_highest >=", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestLessThan(Double value) {
            addCriterion("history_highest <", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestLessThanOrEqualTo(Double value) {
            addCriterion("history_highest <=", value, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestIn(List<Double> values) {
            addCriterion("history_highest in", values, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestNotIn(List<Double> values) {
            addCriterion("history_highest not in", values, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestBetween(Double value1, Double value2) {
            addCriterion("history_highest between", value1, value2, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryHighestNotBetween(Double value1, Double value2) {
            addCriterion("history_highest not between", value1, value2, "historyHighest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestIsNull() {
            addCriterion("history_lowest is null");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestIsNotNull() {
            addCriterion("history_lowest is not null");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestEqualTo(Double value) {
            addCriterion("history_lowest =", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestNotEqualTo(Double value) {
            addCriterion("history_lowest <>", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestGreaterThan(Double value) {
            addCriterion("history_lowest >", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestGreaterThanOrEqualTo(Double value) {
            addCriterion("history_lowest >=", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestLessThan(Double value) {
            addCriterion("history_lowest <", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestLessThanOrEqualTo(Double value) {
            addCriterion("history_lowest <=", value, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestIn(List<Double> values) {
            addCriterion("history_lowest in", values, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestNotIn(List<Double> values) {
            addCriterion("history_lowest not in", values, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestBetween(Double value1, Double value2) {
            addCriterion("history_lowest between", value1, value2, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHistoryLowestNotBetween(Double value1, Double value2) {
            addCriterion("history_lowest not between", value1, value2, "historyLowest");
            return (Criteria) this;
        }

        public Criteria andHighestIsNull() {
            addCriterion("highest is null");
            return (Criteria) this;
        }

        public Criteria andHighestIsNotNull() {
            addCriterion("highest is not null");
            return (Criteria) this;
        }

        public Criteria andHighestEqualTo(Double value) {
            addCriterion("highest =", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestNotEqualTo(Double value) {
            addCriterion("highest <>", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestGreaterThan(Double value) {
            addCriterion("highest >", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestGreaterThanOrEqualTo(Double value) {
            addCriterion("highest >=", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestLessThan(Double value) {
            addCriterion("highest <", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestLessThanOrEqualTo(Double value) {
            addCriterion("highest <=", value, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestIn(List<Double> values) {
            addCriterion("highest in", values, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestNotIn(List<Double> values) {
            addCriterion("highest not in", values, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestBetween(Double value1, Double value2) {
            addCriterion("highest between", value1, value2, "highest");
            return (Criteria) this;
        }

        public Criteria andHighestNotBetween(Double value1, Double value2) {
            addCriterion("highest not between", value1, value2, "highest");
            return (Criteria) this;
        }

        public Criteria andLowestIsNull() {
            addCriterion("lowest is null");
            return (Criteria) this;
        }

        public Criteria andLowestIsNotNull() {
            addCriterion("lowest is not null");
            return (Criteria) this;
        }

        public Criteria andLowestEqualTo(Double value) {
            addCriterion("lowest =", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestNotEqualTo(Double value) {
            addCriterion("lowest <>", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestGreaterThan(Double value) {
            addCriterion("lowest >", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestGreaterThanOrEqualTo(Double value) {
            addCriterion("lowest >=", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestLessThan(Double value) {
            addCriterion("lowest <", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestLessThanOrEqualTo(Double value) {
            addCriterion("lowest <=", value, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestIn(List<Double> values) {
            addCriterion("lowest in", values, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestNotIn(List<Double> values) {
            addCriterion("lowest not in", values, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestBetween(Double value1, Double value2) {
            addCriterion("lowest between", value1, value2, "lowest");
            return (Criteria) this;
        }

        public Criteria andLowestNotBetween(Double value1, Double value2) {
            addCriterion("lowest not between", value1, value2, "lowest");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateIsNull() {
            addCriterion("withdraw_rate is null");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateIsNotNull() {
            addCriterion("withdraw_rate is not null");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateEqualTo(Double value) {
            addCriterion("withdraw_rate =", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateNotEqualTo(Double value) {
            addCriterion("withdraw_rate <>", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateGreaterThan(Double value) {
            addCriterion("withdraw_rate >", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateGreaterThanOrEqualTo(Double value) {
            addCriterion("withdraw_rate >=", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateLessThan(Double value) {
            addCriterion("withdraw_rate <", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateLessThanOrEqualTo(Double value) {
            addCriterion("withdraw_rate <=", value, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateIn(List<Double> values) {
            addCriterion("withdraw_rate in", values, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateNotIn(List<Double> values) {
            addCriterion("withdraw_rate not in", values, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateBetween(Double value1, Double value2) {
            addCriterion("withdraw_rate between", value1, value2, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andWithdrawRateNotBetween(Double value1, Double value2) {
            addCriterion("withdraw_rate not between", value1, value2, "withdrawRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateIsNull() {
            addCriterion("consume_bean_rate is null");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateIsNotNull() {
            addCriterion("consume_bean_rate is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateEqualTo(Double value) {
            addCriterion("consume_bean_rate =", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateNotEqualTo(Double value) {
            addCriterion("consume_bean_rate <>", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateGreaterThan(Double value) {
            addCriterion("consume_bean_rate >", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateGreaterThanOrEqualTo(Double value) {
            addCriterion("consume_bean_rate >=", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateLessThan(Double value) {
            addCriterion("consume_bean_rate <", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateLessThanOrEqualTo(Double value) {
            addCriterion("consume_bean_rate <=", value, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateIn(List<Double> values) {
            addCriterion("consume_bean_rate in", values, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateNotIn(List<Double> values) {
            addCriterion("consume_bean_rate not in", values, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateBetween(Double value1, Double value2) {
            addCriterion("consume_bean_rate between", value1, value2, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andConsumeBeanRateNotBetween(Double value1, Double value2) {
            addCriterion("consume_bean_rate not between", value1, value2, "consumeBeanRate");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountIsNull() {
            addCriterion("max_buy_count is null");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountIsNotNull() {
            addCriterion("max_buy_count is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountEqualTo(Double value) {
            addCriterion("max_buy_count =", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountNotEqualTo(Double value) {
            addCriterion("max_buy_count <>", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountGreaterThan(Double value) {
            addCriterion("max_buy_count >", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountGreaterThanOrEqualTo(Double value) {
            addCriterion("max_buy_count >=", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountLessThan(Double value) {
            addCriterion("max_buy_count <", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountLessThanOrEqualTo(Double value) {
            addCriterion("max_buy_count <=", value, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountIn(List<Double> values) {
            addCriterion("max_buy_count in", values, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountNotIn(List<Double> values) {
            addCriterion("max_buy_count not in", values, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountBetween(Double value1, Double value2) {
            addCriterion("max_buy_count between", value1, value2, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andMaxBuyCountNotBetween(Double value1, Double value2) {
            addCriterion("max_buy_count not between", value1, value2, "maxBuyCount");
            return (Criteria) this;
        }

        public Criteria andRate1IsNull() {
            addCriterion("rate1 is null");
            return (Criteria) this;
        }

        public Criteria andRate1IsNotNull() {
            addCriterion("rate1 is not null");
            return (Criteria) this;
        }

        public Criteria andRate1EqualTo(Double value) {
            addCriterion("rate1 =", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1NotEqualTo(Double value) {
            addCriterion("rate1 <>", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1GreaterThan(Double value) {
            addCriterion("rate1 >", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1GreaterThanOrEqualTo(Double value) {
            addCriterion("rate1 >=", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1LessThan(Double value) {
            addCriterion("rate1 <", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1LessThanOrEqualTo(Double value) {
            addCriterion("rate1 <=", value, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1In(List<Double> values) {
            addCriterion("rate1 in", values, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1NotIn(List<Double> values) {
            addCriterion("rate1 not in", values, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1Between(Double value1, Double value2) {
            addCriterion("rate1 between", value1, value2, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate1NotBetween(Double value1, Double value2) {
            addCriterion("rate1 not between", value1, value2, "rate1");
            return (Criteria) this;
        }

        public Criteria andRate2IsNull() {
            addCriterion("rate2 is null");
            return (Criteria) this;
        }

        public Criteria andRate2IsNotNull() {
            addCriterion("rate2 is not null");
            return (Criteria) this;
        }

        public Criteria andRate2EqualTo(Double value) {
            addCriterion("rate2 =", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2NotEqualTo(Double value) {
            addCriterion("rate2 <>", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2GreaterThan(Double value) {
            addCriterion("rate2 >", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2GreaterThanOrEqualTo(Double value) {
            addCriterion("rate2 >=", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2LessThan(Double value) {
            addCriterion("rate2 <", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2LessThanOrEqualTo(Double value) {
            addCriterion("rate2 <=", value, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2In(List<Double> values) {
            addCriterion("rate2 in", values, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2NotIn(List<Double> values) {
            addCriterion("rate2 not in", values, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2Between(Double value1, Double value2) {
            addCriterion("rate2 between", value1, value2, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate2NotBetween(Double value1, Double value2) {
            addCriterion("rate2 not between", value1, value2, "rate2");
            return (Criteria) this;
        }

        public Criteria andRate3IsNull() {
            addCriterion("rate3 is null");
            return (Criteria) this;
        }

        public Criteria andRate3IsNotNull() {
            addCriterion("rate3 is not null");
            return (Criteria) this;
        }

        public Criteria andRate3EqualTo(Double value) {
            addCriterion("rate3 =", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3NotEqualTo(Double value) {
            addCriterion("rate3 <>", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3GreaterThan(Double value) {
            addCriterion("rate3 >", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3GreaterThanOrEqualTo(Double value) {
            addCriterion("rate3 >=", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3LessThan(Double value) {
            addCriterion("rate3 <", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3LessThanOrEqualTo(Double value) {
            addCriterion("rate3 <=", value, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3In(List<Double> values) {
            addCriterion("rate3 in", values, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3NotIn(List<Double> values) {
            addCriterion("rate3 not in", values, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3Between(Double value1, Double value2) {
            addCriterion("rate3 between", value1, value2, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate3NotBetween(Double value1, Double value2) {
            addCriterion("rate3 not between", value1, value2, "rate3");
            return (Criteria) this;
        }

        public Criteria andRate4IsNull() {
            addCriterion("rate4 is null");
            return (Criteria) this;
        }

        public Criteria andRate4IsNotNull() {
            addCriterion("rate4 is not null");
            return (Criteria) this;
        }

        public Criteria andRate4EqualTo(Double value) {
            addCriterion("rate4 =", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4NotEqualTo(Double value) {
            addCriterion("rate4 <>", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4GreaterThan(Double value) {
            addCriterion("rate4 >", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4GreaterThanOrEqualTo(Double value) {
            addCriterion("rate4 >=", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4LessThan(Double value) {
            addCriterion("rate4 <", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4LessThanOrEqualTo(Double value) {
            addCriterion("rate4 <=", value, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4In(List<Double> values) {
            addCriterion("rate4 in", values, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4NotIn(List<Double> values) {
            addCriterion("rate4 not in", values, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4Between(Double value1, Double value2) {
            addCriterion("rate4 between", value1, value2, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate4NotBetween(Double value1, Double value2) {
            addCriterion("rate4 not between", value1, value2, "rate4");
            return (Criteria) this;
        }

        public Criteria andRate5IsNull() {
            addCriterion("rate5 is null");
            return (Criteria) this;
        }

        public Criteria andRate5IsNotNull() {
            addCriterion("rate5 is not null");
            return (Criteria) this;
        }

        public Criteria andRate5EqualTo(Double value) {
            addCriterion("rate5 =", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5NotEqualTo(Double value) {
            addCriterion("rate5 <>", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5GreaterThan(Double value) {
            addCriterion("rate5 >", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5GreaterThanOrEqualTo(Double value) {
            addCriterion("rate5 >=", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5LessThan(Double value) {
            addCriterion("rate5 <", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5LessThanOrEqualTo(Double value) {
            addCriterion("rate5 <=", value, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5In(List<Double> values) {
            addCriterion("rate5 in", values, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5NotIn(List<Double> values) {
            addCriterion("rate5 not in", values, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5Between(Double value1, Double value2) {
            addCriterion("rate5 between", value1, value2, "rate5");
            return (Criteria) this;
        }

        public Criteria andRate5NotBetween(Double value1, Double value2) {
            addCriterion("rate5 not between", value1, value2, "rate5");
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