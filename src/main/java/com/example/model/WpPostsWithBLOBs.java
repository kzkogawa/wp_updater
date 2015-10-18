package com.example.model;

public class WpPostsWithBLOBs extends WpPosts {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.post_content
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String postContent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.post_title
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String postTitle;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.post_excerpt
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String postExcerpt;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.to_ping
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String toPing;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.pinged
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String pinged;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column wp_posts.post_content_filtered
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	private String postContentFiltered;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.post_content
	 * @return  the value of wp_posts.post_content
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getPostContent() {
		return postContent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.post_content
	 * @param postContent  the value for wp_posts.post_content
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setPostContent(String postContent) {
		this.postContent = postContent == null ? null : postContent.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.post_title
	 * @return  the value of wp_posts.post_title
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getPostTitle() {
		return postTitle;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.post_title
	 * @param postTitle  the value for wp_posts.post_title
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle == null ? null : postTitle.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.post_excerpt
	 * @return  the value of wp_posts.post_excerpt
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getPostExcerpt() {
		return postExcerpt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.post_excerpt
	 * @param postExcerpt  the value for wp_posts.post_excerpt
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setPostExcerpt(String postExcerpt) {
		this.postExcerpt = postExcerpt == null ? null : postExcerpt.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.to_ping
	 * @return  the value of wp_posts.to_ping
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getToPing() {
		return toPing;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.to_ping
	 * @param toPing  the value for wp_posts.to_ping
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setToPing(String toPing) {
		this.toPing = toPing == null ? null : toPing.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.pinged
	 * @return  the value of wp_posts.pinged
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getPinged() {
		return pinged;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.pinged
	 * @param pinged  the value for wp_posts.pinged
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setPinged(String pinged) {
		this.pinged = pinged == null ? null : pinged.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column wp_posts.post_content_filtered
	 * @return  the value of wp_posts.post_content_filtered
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public String getPostContentFiltered() {
		return postContentFiltered;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column wp_posts.post_content_filtered
	 * @param postContentFiltered  the value for wp_posts.post_content_filtered
	 * @mbggenerated  Sat Oct 17 17:42:36 PDT 2015
	 */
	public void setPostContentFiltered(String postContentFiltered) {
		this.postContentFiltered = postContentFiltered == null ? null : postContentFiltered.trim();
	}
}