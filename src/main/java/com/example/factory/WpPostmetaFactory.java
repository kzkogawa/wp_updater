package com.example.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.PostServiceModel;
import com.example.model.wp.WpPostmeta;
import com.example.util.WpUpdaterUtils;

public class WpPostmetaFactory {
	private static final Logger log = LoggerFactory.getLogger(WpPostmetaFactory.class);

	public static WpPostmeta getPostEditLastMetas(Long postId, PostServiceModel serviceModel) {
		WpPostmeta editLast = new WpPostmeta();
		editLast.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LAST);
		editLast.setMetaValue("1");
		editLast.setPostId(postId);
		return editLast;
	}

	public static WpPostmeta getPostEditLockMetas(Long postId, PostServiceModel serviceModel) {
		WpPostmeta editLock = new WpPostmeta();
		editLock.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LOCK);
		editLock.setMetaValue(WpUpdaterUtils.getCurrentUtcTime().getTime() / 1000 + ":1");
		editLock.setPostId(postId);
		return editLock;
	}

	public static WpPostmeta getPostThmIdMetas(Long postId, Long imageId, PostServiceModel serviceModel) {
		WpPostmeta thmId = new WpPostmeta();
		thmId.setMetaKey(WpUpdaterUtils.CONST_POST_META_THUMB_ID);
		thmId.setPostId(postId);
		thmId.setMetaValue(String.valueOf(imageId));
		return thmId;
	}

	public static WpPostmeta getImageAttachedFileMetas(Long postId, PostServiceModel serviceModel) {
		WpPostmeta attachedFile = new WpPostmeta();
		attachedFile.setPostId(postId);
		attachedFile.setMetaKey(WpUpdaterUtils.CONST_POST_META_ATTACH_FILE);
		attachedFile.setMetaValue(WpUpdaterUtils.getAttacheMetaValue(serviceModel.getImageFileNameWithExtension()));
		return attachedFile;
	}
}
