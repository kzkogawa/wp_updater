package com.example.model;

public class WpTermRelationships extends WpTermRelationshipsKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private Integer termOrder;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_relationships.term_order
	 * @return  the value of wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public Integer getTermOrder() {
		return termOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_relationships.term_order
	 * @param termOrder  the value for wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setTermOrder(Integer termOrder) {
		this.termOrder = termOrder;
	}
}