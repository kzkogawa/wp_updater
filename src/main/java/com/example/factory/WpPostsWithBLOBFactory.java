package com.example.factory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.PostServiceModel;
import com.example.model.wp.WpPostsWithBLOBs;
import com.example.util.WpUpdaterUtils;

public class WpPostsWithBLOBFactory {
	private static final Logger log = LoggerFactory.getLogger(WpPostsWithBLOBFactory.class);

	public static WpPostsWithBLOBs getPostData(PostServiceModel serviceModel) {
		WpPostsWithBLOBs post = new WpPostsWithBLOBs();
		post.setPostParent(0L);
		post.setPostType(WpUpdaterUtils.CONST_POST_TYPE_POST);
		post.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_PUBLISH);
		post.setPostTitle(serviceModel.getConvertedPostTitle());
		post.setPostName(WpUpdaterUtils.urlEncode(StringUtils.substring(serviceModel.getConvertedPostTitle(), 0, 22)).toLowerCase());
		post.setPostContent(serviceModel.getPostContent());
		log.debug("", post);
		return post;
	}

	public static WpPostsWithBLOBs getRevisionData(Long postId, PostServiceModel serviceModel) {
		WpPostsWithBLOBs revision = new WpPostsWithBLOBs();
		revision.setPostType(WpUpdaterUtils.CONST_POST_TYPE_REVISION);
		revision.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_INHERIT);
		revision.setPostTitle(serviceModel.getPostTitle());
		revision.setPostContent(serviceModel.getPostContent());
		revision.setPostName(String.format("%s-revision-v1", postId));
		revision.setPostParent(postId);
		log.debug("", postId);
		return revision;
	}

	public static WpPostsWithBLOBs getImageData(Long postId, PostServiceModel serviceModel) {
		WpPostsWithBLOBs image = new WpPostsWithBLOBs();
		image.setPostType(WpUpdaterUtils.CONST_POST_TYPE_ATTACH);
		image.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_INHERIT);
		image.setPostContent("");
		image.setPostParent(postId);
		if (StringUtils.isNotEmpty(serviceModel.getPostImageUrl())) {
			image.setPostTitle(serviceModel.getImageFileName());
			image.setPostName(serviceModel.getImageFileName());
			image.setPostMimeType("image/" + serviceModel.getImageFileExtension());
			image.setGuid(WpUpdaterUtils.getAttachGuid(serviceModel.getImageFileNameWithExtension()));
			image.setImageInfo(WpUpdaterUtils.saveImage(serviceModel.getPostImageUrl(), serviceModel.getImageFileNameWithExtension()));
		}
		log.debug("", image);
		return image;
	}
}
