package com.example.model.wp;

public class WpTerms {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_terms.term_id
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	private Long termId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_terms.name
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_terms.slug
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	private String slug;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_terms.term_group
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	private Long termGroup;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_terms.term_id
	 * @return  the value of wp_terms.term_id
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public Long getTermId() {
		return termId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_terms.term_id
	 * @param termId  the value for wp_terms.term_id
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public void setTermId(Long termId) {
		this.termId = termId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_terms.name
	 * @return  the value of wp_terms.name
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_terms.name
	 * @param name  the value for wp_terms.name
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_terms.slug
	 * @return  the value of wp_terms.slug
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public String getSlug() {
		return slug;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_terms.slug
	 * @param slug  the value for wp_terms.slug
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public void setSlug(String slug) {
		this.slug = slug == null ? null : slug.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_terms.term_group
	 * @return  the value of wp_terms.term_group
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public Long getTermGroup() {
		return termGroup;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_terms.term_group
	 * @param termGroup  the value for wp_terms.term_group
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	public void setTermGroup(Long termGroup) {
		this.termGroup = termGroup;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_terms
	 * @mbggenerated  Sun Nov 01 12:34:26 PST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", termId=").append(termId);
		sb.append(", name=").append(name);
		sb.append(", slug=").append(slug);
		sb.append(", termGroup=").append(termGroup);
		sb.append("]");
		return sb.toString();
	}

	public WpTerms() {
		setTermGroup(0L);
	}
}