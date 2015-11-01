package com.example.factory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.model.PostServiceModel;
import com.example.model.wp.WpPostmeta;
import com.example.model.wp.WpPostsWithBLOBs;
import com.example.util.WpUpdaterUtils;

@Component
public class WpPostmetaFactory {
	private static String WP_ATT_META;
	@Autowired
	private Environment env;
	private static final Logger log = LoggerFactory.getLogger(WpPostmetaFactory.class);

	@Value("${wp.attachment.metadata}")
	public void setPrivateName(String value) {
		WpPostmetaFactory.WP_ATT_META = value;
	}

	public static WpPostmeta getPostEditLastMetas(Long postId, PostServiceModel serviceModel) {
		WpPostmeta editLast = new WpPostmeta();
		editLast.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LAST);
		editLast.setMetaValue("1");
		editLast.setPostId(postId);
		return editLast;
	}

	public static WpPostmeta getPostEditLockMeta(Long postId, PostServiceModel serviceModel) {
		WpPostmeta editLock = new WpPostmeta();
		editLock.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LOCK);
		editLock.setMetaValue(WpUpdaterUtils.getCurrentUtcTime().getTime() / 1000 + ":1");
		editLock.setPostId(postId);
		return editLock;
	}

	public static WpPostmeta getPostThmIdMeta(Long postId, Long imageId, PostServiceModel serviceModel) {
		WpPostmeta thmId = new WpPostmeta();
		thmId.setMetaKey(WpUpdaterUtils.CONST_POST_META_THUMB_ID);
		thmId.setPostId(postId);
		thmId.setMetaValue(String.valueOf(imageId));
		return thmId;
	}

	public static WpPostmeta getImageAttachedFileMeta(Long postId, PostServiceModel serviceModel) {
		WpPostmeta attachedFile = new WpPostmeta();
		attachedFile.setPostId(postId);
		attachedFile.setMetaKey(WpUpdaterUtils.CONST_POST_META_ATTACH_FILE);
		attachedFile.setMetaValue(WpUpdaterUtils.getAttacheMetaValue(serviceModel.getImageFileNameWithExtension()));
		return attachedFile;
	}

	public static WpPostmeta getAttachmentMeta(WpPostsWithBLOBs image, String metaValue) {
		Map<String, Integer> imgInfo = image.getImageInfo();
		if (imgInfo != null) {
			String val = String.format(WP_ATT_META, imgInfo.get("width"), imgInfo.get("height"), metaValue.length(), metaValue);
			WpPostmeta attachmentMetadata = new WpPostmeta();
			attachmentMetadata.setPostId(image.getId());
			attachmentMetadata.setMetaKey(WpUpdaterUtils.CONST_POST_META_ATTACH_META);
			attachmentMetadata.setMetaValue(val);
			return attachmentMetadata;
		}
		log.debug("imgInfo is null {},{}", image, metaValue);
		return null;
	}
}
