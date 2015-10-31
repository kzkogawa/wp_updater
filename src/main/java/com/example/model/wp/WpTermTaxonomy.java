package com.example.model.wp;

public class WpTermTaxonomy {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.term_taxonomy_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private Long termTaxonomyId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.term_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private Long termId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.taxonomy
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private String taxonomy;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.parent
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private Long parent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.count
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private Long count;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_term_taxonomy.description
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.term_taxonomy_id
	 * @return  the value of wp_term_taxonomy.term_taxonomy_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Long getTermTaxonomyId() {
		return termTaxonomyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.term_taxonomy_id
	 * @param termTaxonomyId  the value for wp_term_taxonomy.term_taxonomy_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setTermTaxonomyId(Long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.term_id
	 * @return  the value of wp_term_taxonomy.term_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Long getTermId() {
		return termId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.term_id
	 * @param termId  the value for wp_term_taxonomy.term_id
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setTermId(Long termId) {
		this.termId = termId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.taxonomy
	 * @return  the value of wp_term_taxonomy.taxonomy
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public String getTaxonomy() {
		return taxonomy;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.taxonomy
	 * @param taxonomy  the value for wp_term_taxonomy.taxonomy
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy == null ? null : taxonomy.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.parent
	 * @return  the value of wp_term_taxonomy.parent
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Long getParent() {
		return parent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.parent
	 * @param parent  the value for wp_term_taxonomy.parent
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setParent(Long parent) {
		this.parent = parent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.count
	 * @return  the value of wp_term_taxonomy.count
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.count
	 * @param count  the value for wp_term_taxonomy.count
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_term_taxonomy.description
	 * @return  the value of wp_term_taxonomy.description
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_term_taxonomy.description
	 * @param description  the value for wp_term_taxonomy.description
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_term_taxonomy
	 * @mbggenerated  Thu Oct 29 22:05:09 PDT 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", termTaxonomyId=").append(termTaxonomyId);
		sb.append(", termId=").append(termId);
		sb.append(", taxonomy=").append(taxonomy);
		sb.append(", parent=").append(parent);
		sb.append(", count=").append(count);
		sb.append(", description=").append(description);
		sb.append("]");
		return sb.toString();
	}

	public WpTermTaxonomy() {
		setDescription("");
		setParent(0L);
		setCount(1L);
	}
}