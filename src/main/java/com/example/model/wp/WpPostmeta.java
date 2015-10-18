package com.example.model.wp;

public class WpPostmeta {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_postmeta.meta_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	private Long metaId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_postmeta.post_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	private Long postId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_postmeta.meta_key
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	private String metaKey;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_postmeta.meta_value
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	private String metaValue;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_postmeta.meta_id
	 * @return  the value of wp_postmeta.meta_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public Long getMetaId() {
		return metaId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_postmeta.meta_id
	 * @param metaId  the value for wp_postmeta.meta_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public void setMetaId(Long metaId) {
		this.metaId = metaId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_postmeta.post_id
	 * @return  the value of wp_postmeta.post_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public Long getPostId() {
		return postId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_postmeta.post_id
	 * @param postId  the value for wp_postmeta.post_id
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_postmeta.meta_key
	 * @return  the value of wp_postmeta.meta_key
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public String getMetaKey() {
		return metaKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_postmeta.meta_key
	 * @param metaKey  the value for wp_postmeta.meta_key
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey == null ? null : metaKey.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_postmeta.meta_value
	 * @return  the value of wp_postmeta.meta_value
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public String getMetaValue() {
		return metaValue;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_postmeta.meta_value
	 * @param metaValue  the value for wp_postmeta.meta_value
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue == null ? null : metaValue.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wp_postmeta
	 * @mbggenerated  Sat Oct 17 23:13:40 PDT 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", metaId=").append(metaId);
		sb.append(", postId=").append(postId);
		sb.append(", metaKey=").append(metaKey);
		sb.append(", metaValue=").append(metaValue);
		sb.append("]");
		return sb.toString();
	}
}