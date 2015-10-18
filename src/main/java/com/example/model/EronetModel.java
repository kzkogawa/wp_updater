package com.example.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class EronetModel {
	public EronetModel() {
		videoUrls = new ArrayList<String>();
	}

	private String ImageSrc;
	private String ImageAlt;
	private String ImageLink;
	private List<String> videoUrls;

	public String getImageSrc() {
		return ImageSrc;
	}

	public void setImageSrc(String imageSrc) {
		ImageSrc = imageSrc;
	}

	public String getImageAlt() {
		return ImageAlt;
	}

	public void setImageAlt(String imageAlt) {
		ImageAlt = imageAlt;
	}

	public String getImageLink() {
		return ImageLink;
	}

	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}

	public List<String> getVideoUrls() {
		return videoUrls;
	}

	public void setVideoUrls(List<String> videoUrls) {
		this.videoUrls = videoUrls;
	};

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
