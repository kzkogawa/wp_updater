package com.example.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.example.factory.WpPostmetaFactory;
import com.example.factory.WpPostsWithBLOBFactory;
import com.example.mapper.EmbeddedTagMapper;
import com.example.mapper.WpPostmetaMapper;
import com.example.mapper.WpPostsMapper;
import com.example.mapper.WpTermRelationshipsMapper;
import com.example.model.PostServiceModel;
import com.example.model.wp.EmbeddedTag;
import com.example.model.wp.EmbeddedTagCriteria;
import com.example.model.wp.WpPostmeta;
import com.example.model.wp.WpPostsCriteria;
import com.example.model.wp.WpPostsWithBLOBs;
import com.example.model.wp.WpTermRelationships;
import com.example.util.WpUpdaterUtils;

@Component
public class PostService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private Environment env;
	@Autowired
	private WpPostsMapper postsMapper;
	@Autowired
	private WpPostmetaMapper postmetaMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;
	@Autowired
	private TermService termService;
	@Autowired
	private EmbeddedTagMapper embeddedTagMapper;

	public void InsertPosts(List<PostServiceModel> serviceModels) {
		log.debug("inserting serviceModels.size={}", serviceModels.size());
		for (PostServiceModel serviceModel : serviceModels) {
			WpPostsWithBLOBs post = WpPostsWithBLOBFactory.getPostData(serviceModel);
			WpPostsCriteria wpPostsCriteria = new WpPostsCriteria();
			wpPostsCriteria.createCriteria().andPostNameLike(post.getPostName()).andPostStatusNotEqualTo(WpUpdaterUtils.CONST_POST_STATUS_TRASH);
			if (postsMapper.selectByExample(wpPostsCriteria).size() == 0) {
				// insert post
				postsMapper.insert(post);
				post.setGuid(WpUpdaterUtils.getPostGuid(post.getId()));
				postsMapper.updateByPrimaryKeyWithBLOBs(post);
				// insert revision
				WpPostsWithBLOBs revision = WpPostsWithBLOBFactory.getRevisionData(post.getId(), serviceModel);
				postsMapper.insert(revision);
				revision.setGuid(WpUpdaterUtils.getRevisionGuid(revision.getId()));
				postsMapper.updateByPrimaryKeyWithBLOBs(revision);
				// insert image
				WpPostsWithBLOBs image = WpPostsWithBLOBFactory.getImageData(post.getId(), serviceModel);
				postsMapper.insert(image);

				// insert postmetas
				WpPostmeta editLastMeta = WpPostmetaFactory.getPostEditLastMetas(post.getId(), serviceModel);
				postmetaMapper.insert(editLastMeta);
				WpPostmeta editLockMeta = WpPostmetaFactory.getPostEditLockMetas(post.getId(), serviceModel);
				postmetaMapper.insert(editLockMeta);
				WpPostmeta thmIdMeta = WpPostmetaFactory.getPostThmIdMetas(post.getId(), image.getId(), serviceModel);
				postmetaMapper.insert(thmIdMeta);
				WpPostmeta attachedFileMeta = WpPostmetaFactory.getImageAttachedFileMetas(image.getId(), serviceModel);
				postmetaMapper.insert(attachedFileMeta);

				Map<String, Integer> imgInfo = image.getImageInfo();
				if (imgInfo != null) {
					String val = String.format(env.getProperty("wp.attachment.metadata"), imgInfo.get("width"), imgInfo.get("height"), attachedFileMeta.getMetaValue());
					WpPostmeta attachmentMetadata = new WpPostmeta();
					attachmentMetadata.setPostId(image.getId());
					attachmentMetadata.setMetaKey(WpUpdaterUtils.CONST_POST_META_ATTACH_META);
					attachmentMetadata.setMetaValue(val);
					postmetaMapper.insert(attachmentMetadata);
				}

				List<EmbeddedTag> embeddedTags = embeddedTagMapper.selectByExample(new EmbeddedTagCriteria());
				for (EmbeddedTag embeddedTag : embeddedTags) {
					if (StringUtils.contains(serviceModel.getPostTitle().toLowerCase(), embeddedTag.getKeyword().toLowerCase())) {
						serviceModel.addTag(embeddedTag.getTag());
					}
				}

				for (String tag : serviceModel.getTags()) {
					WpTermRelationships termRelationships = termService.getWpTermRelationshipsByTagName(tag);
					termRelationships.setObjectId(post.getId());
					relationshipsMapper.insert(termRelationships);
				}
				for (String category : serviceModel.getCategorys()) {
					WpTermRelationships termRelationships = termService.getWpTermRelationshipsByCategoryName(category);
					termRelationships.setObjectId(post.getId());
					relationshipsMapper.insert(termRelationships);
				}
			} else {
				log.info("Same post found {}", post);
			}
		}
		termService.updateUsageCount();
	}
}
