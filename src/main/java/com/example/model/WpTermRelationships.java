package com.example.model;

public class WpTermRelationships extends WpTermRelationshipsKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 20:06:25 PDT 2015
	 */
	private Integer termOrder;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_relationships.term_order
	 * @return  the value of wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 20:06:25 PDT 2015
	 */
	public Integer getTermOrder() {
		return termOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_relationships.term_order
	 * @param termOrder  the value for wp_term_relationships.term_order
	 * @mbggenerated  Sat Oct 17 20:06:25 PDT 2015
	 */
	public void setTermOrder(Integer termOrder) {
		this.termOrder = termOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_relationships
	 * @mbggenerated  Sat Oct 17 20:06:25 PDT 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", termOrder=").append(termOrder);
		sb.append("]");
		return sb.toString();
	}
}