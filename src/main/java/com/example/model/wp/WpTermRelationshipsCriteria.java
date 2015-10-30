package com.example.model.wp;

import java.util.ArrayList;
import java.util.List;

public class WpTermRelationshipsCriteria {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public WpTermRelationshipsCriteria() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
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

		public Criteria andObjectIdIsNull() {
			addCriterion("object_id is null");
			return (Criteria) this;
		}

		public Criteria andObjectIdIsNotNull() {
			addCriterion("object_id is not null");
			return (Criteria) this;
		}

		public Criteria andObjectIdEqualTo(Long value) {
			addCriterion("object_id =", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdNotEqualTo(Long value) {
			addCriterion("object_id <>", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdGreaterThan(Long value) {
			addCriterion("object_id >", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdGreaterThanOrEqualTo(Long value) {
			addCriterion("object_id >=", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdLessThan(Long value) {
			addCriterion("object_id <", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdLessThanOrEqualTo(Long value) {
			addCriterion("object_id <=", value, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdIn(List<Long> values) {
			addCriterion("object_id in", values, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdNotIn(List<Long> values) {
			addCriterion("object_id not in", values, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdBetween(Long value1, Long value2) {
			addCriterion("object_id between", value1, value2, "objectId");
			return (Criteria) this;
		}

		public Criteria andObjectIdNotBetween(Long value1, Long value2) {
			addCriterion("object_id not between", value1, value2, "objectId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdIsNull() {
			addCriterion("term_taxonomy_id is null");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdIsNotNull() {
			addCriterion("term_taxonomy_id is not null");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdEqualTo(Long value) {
			addCriterion("term_taxonomy_id =", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdNotEqualTo(Long value) {
			addCriterion("term_taxonomy_id <>", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdGreaterThan(Long value) {
			addCriterion("term_taxonomy_id >", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdGreaterThanOrEqualTo(Long value) {
			addCriterion("term_taxonomy_id >=", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdLessThan(Long value) {
			addCriterion("term_taxonomy_id <", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdLessThanOrEqualTo(Long value) {
			addCriterion("term_taxonomy_id <=", value, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdIn(List<Long> values) {
			addCriterion("term_taxonomy_id in", values, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdNotIn(List<Long> values) {
			addCriterion("term_taxonomy_id not in", values, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdBetween(Long value1, Long value2) {
			addCriterion("term_taxonomy_id between", value1, value2, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermTaxonomyIdNotBetween(Long value1, Long value2) {
			addCriterion("term_taxonomy_id not between", value1, value2, "termTaxonomyId");
			return (Criteria) this;
		}

		public Criteria andTermOrderIsNull() {
			addCriterion("term_order is null");
			return (Criteria) this;
		}

		public Criteria andTermOrderIsNotNull() {
			addCriterion("term_order is not null");
			return (Criteria) this;
		}

		public Criteria andTermOrderEqualTo(Integer value) {
			addCriterion("term_order =", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderNotEqualTo(Integer value) {
			addCriterion("term_order <>", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderGreaterThan(Integer value) {
			addCriterion("term_order >", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("term_order >=", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderLessThan(Integer value) {
			addCriterion("term_order <", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderLessThanOrEqualTo(Integer value) {
			addCriterion("term_order <=", value, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderIn(List<Integer> values) {
			addCriterion("term_order in", values, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderNotIn(List<Integer> values) {
			addCriterion("term_order not in", values, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderBetween(Integer value1, Integer value2) {
			addCriterion("term_order between", value1, value2, "termOrder");
			return (Criteria) this;
		}

		public Criteria andTermOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("term_order not between", value1, value2, "termOrder");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table wp_term_relationships
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
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

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table wp_term_relationships
     *
     * @mbggenerated do_not_delete_during_merge Sat Oct 17 22:04:30 PDT 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}