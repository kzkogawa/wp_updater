package com.example.factory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.wp.WpPostmeta;
import com.example.model.wp.WpPostsWithBLOBs;
import com.example.util.WpUpdaterUtils;

public class WpPostsWithBLOBFactory {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private WpPostsWithBLOBs post = new WpPostsWithBLOBs();
	private WpPostsWithBLOBs revision = new WpPostsWithBLOBs();
	private WpPostsWithBLOBs image = new WpPostsWithBLOBs();
	private WpPostmeta editLast = new WpPostmeta();
	private WpPostmeta editLock = new WpPostmeta();
	private WpPostmeta thmId = new WpPostmeta();
	private WpPostmeta attachedFile = new WpPostmeta();
	private String url;

	// private WpPostmeta jetpack = new WpPostmeta();
	// private WpPostmeta fileMeta = new WpPostmeta();

	public WpPostsWithBLOBFactory() {
		post.setPostParent(0L);
		post.setPostType(WpUpdaterUtils.CONST_POST_TYPE_POST);
		post.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_PUBLISH);
		revision.setPostType(WpUpdaterUtils.CONST_POST_TYPE_REVISION);
		revision.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_INHERIT);
		image.setPostType(WpUpdaterUtils.CONST_POST_TYPE_ATTACH);
		image.setPostStatus(WpUpdaterUtils.CONST_POST_STATUS_INHERIT);
		editLast.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LAST);
		editLast.setMetaValue("1");
		editLock.setMetaKey(WpUpdaterUtils.CONST_POST_META_EDIT_LOCK);
		editLock.setMetaValue(WpUpdaterUtils.getCurrentUtcTime().getTime() / 1000 + ":1");
		thmId.setMetaKey(WpUpdaterUtils.CONST_POST_META_THUMB_ID);
		attachedFile.setMetaKey(WpUpdaterUtils.CONST_POST_META_ATTACH_FILE);
	}

	public WpPostsWithBLOBFactory setPostTitle(String title) {
		post.setPostTitle(title);
		post.setPostName(WpUpdaterUtils.urlEncode(StringUtils.substring(title, 0, 22)).toLowerCase());
		revision.setPostTitle(title);
		return this;
	}

	public WpPostsWithBLOBFactory setPostContent(String content) {
		post.setPostContent(content);
		revision.setPostContent(content);
		image.setPostContent("");
		return this;
	}

	public WpPostsWithBLOBFactory setImageLink(String url) {
		this.url = url;
		return this;
	}

	// TODO: should be immutable
	// return cloned object
	// and create id-setter for these posts object
	// and maybe need validation like being called with correct order or
	// something

	public WpPostsWithBLOBs getPostForInsert() {
		return post;
	}

	public WpPostsWithBLOBFactory preparePostUpdate() {
		revision.setPostName(String.format("%s-revision-v1", post.getId()));
		revision.setPostParent(post.getId());
		image.setPostParent(post.getId());
		post.setGuid(WpUpdaterUtils.getPostGuid(post.getId()));
		editLast.setPostId(post.getId());
		editLock.setPostId(post.getId());
		thmId.setPostId(post.getId());
		return this;
	}

	public WpPostsWithBLOBs getPostForUpdate() {
		// TODO:validation
		return post;
	}

	public WpPostsWithBLOBs getRevisionForInsert() {
		return revision;
	}

	public WpPostsWithBLOBFactory prepareRevisionUpdate() {
		revision.setGuid(WpUpdaterUtils.getRevisionGuid(revision.getId()));
		return this;
	}

	public WpPostsWithBLOBs getRevisionForUpdate() {
		return revision;
	}

	public WpPostsWithBLOBs getImageForInsert() {
		if (StringUtils.isNotEmpty(url)) {
			String extension = FilenameUtils.getExtension(url);
			String fileName = String.valueOf(WpUpdaterUtils.getCurrentUtcTime().getTime());
			String fileNameEx = String.format("%s.%s", fileName, extension);
			image.setPostTitle(fileName);
			image.setPostName(fileName);
			image.setPostMimeType("image/" + extension);
			image.setGuid(WpUpdaterUtils.getAttachGuid(fileNameEx));
			attachedFile.setMetaValue(WpUpdaterUtils.getAttacheMetaValue(fileNameEx));
			try {
				FileUtils.copyURLToFile(new URL(url), WpUpdaterUtils.getUploadFile(fileNameEx));
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return image;
	}

	public WpPostsWithBLOBFactory prepareImageUpdate() {
		thmId.setMetaValue(String.valueOf(image.getId()));
		attachedFile.setPostId(image.getId());
		return this;
	}

	public WpPostsWithBLOBs getImageForUpdate() {
		return image;
	}

	public List<WpPostmeta> getPostMetas() {
		List<WpPostmeta> ret = new ArrayList<WpPostmeta>();
		ret.add(editLast);
		ret.add(editLock);
		ret.add(thmId);
		ret.add(attachedFile);
		return ret;
	}
}
