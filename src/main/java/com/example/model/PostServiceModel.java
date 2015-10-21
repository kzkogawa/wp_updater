package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.example.util.WpUpdaterUtils;
import com.mysql.jdbc.StringUtils;

public class PostServiceModel {
	private String orgPageUrl;
	private String postSourceUrl;
	private String postTitle;
	private String postImageUrl;
	private List<String> videoUrls = new ArrayList<String>();
	private List<String> tags = new ArrayList<String>();
	private List<String> categorys = new ArrayList<String>();
	private String template;
	private String imageFileName;
	private String postContent;

	public PostServiceModel(String template) {
		this.template = template;
	}

	/**
	 * @return the orgPageUrl
	 */
	public String getOrgPageUrl() {
		return orgPageUrl;
	}

	/**
	 * @param orgPageUrl
	 *            the orgPageUrl to set
	 */
	public void setOrgPageUrl(String orgPageUrl) {
		this.orgPageUrl = orgPageUrl;
	}

	/**
	 * @return the postSourceUrl
	 */
	public String getPostSourceUrl() {
		return postSourceUrl;
	}

	/**
	 * @param postSourceUrl
	 *            the postSourceUrl to set
	 */
	public void setPostSourceUrl(String postSourceUrl) {
		this.postSourceUrl = postSourceUrl;
	}

	/**
	 * @return the postTitle
	 */
	public String getPostTitle() {
		return postTitle;
	}

	/**
	 * @param postTitle
	 *            the postTitle to set
	 */
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	/**
	 * @return the postImageUrl
	 */
	public String getPostImageUrl() {
		return postImageUrl;
	}

	/**
	 * @param postImageUrl
	 *            the postImageUrl to set
	 */
	public void setPostImageUrl(String postImageUrl) {
		this.postImageUrl = postImageUrl;
	}

	/**
	 * @return the videoUrls
	 */
	public List<String> getVideoUrls() {
		return videoUrls;
	}

	/**
	 * @param videoUrls
	 *            the videoUrls to set
	 */
	public void addVideoUrls(String videoUrl) {
		this.videoUrls.add(videoUrl);
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void addTag(String Tag) {
		this.tags.add(Tag);
	}

	/**
	 * @return the categorys
	 */
	public List<String> getCategorys() {
		return categorys;
	}

	/**
	 * @param categorys
	 *            the categorys to set
	 */
	public void addCategorys(String category) {
		this.categorys.add(category);
	}

	public String getPostContent() {
		if (!StringUtils.isNullOrEmpty(template) && StringUtils.isNullOrEmpty(postContent)) {
			postContent = WpUpdaterUtils.getContentFromTemplate(template, this);
		}
		return postContent;
	}

	public String getImageFileName() {
		if (StringUtils.isNullOrEmpty(imageFileName)) {
			imageFileName = String.valueOf(WpUpdaterUtils.getCurrentUtcTime().getTime());
		}
		return imageFileName;
	}

	public String getImageFileExtension() {
		return FilenameUtils.getExtension(postImageUrl);
	}

	public String getImageFileNameWithExtension() {
		return String.format("%s.%s", getImageFileName(), getImageFileExtension());
	}

}
