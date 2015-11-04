package com.example.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.factory.WpPostmetaFactory;
import com.example.factory.WpPostsWithBLOBFactory;
import com.example.mapper.EmbeddedCategoryMapper;
import com.example.mapper.EmbeddedTagMapper;
import com.example.mapper.WpPostmetaMapper;
import com.example.mapper.WpPostsMapper;
import com.example.mapper.WpTermRelationshipsMapper;
import com.example.model.PostServiceModel;
import com.example.model.wp.EmbeddedCategory;
import com.example.model.wp.EmbeddedCategoryCriteria;
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
	private WpPostsMapper postsMapper;
	@Autowired
	private WpPostmetaMapper postmetaMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;
	@Autowired
	private TermService termService;
	@Autowired
	private EmbeddedTagMapper embeddedTagMapper;
	@Autowired
	private EmbeddedCategoryMapper embeddedCategoryMapper;

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
				WpPostmeta editLockMeta = WpPostmetaFactory.getPostEditLockMeta(post.getId(), serviceModel);
				postmetaMapper.insert(editLockMeta);
				WpPostmeta thmIdMeta = WpPostmetaFactory.getPostThmIdMeta(post.getId(), image.getId(), serviceModel);
				postmetaMapper.insert(thmIdMeta);
				WpPostmeta attachedFileMeta = WpPostmetaFactory.getImageAttachedFileMeta(image.getId(), serviceModel);
				postmetaMapper.insert(attachedFileMeta);
				WpPostmeta attachmentMetadata = WpPostmetaFactory.getAttachmentMeta(image, attachedFileMeta.getMetaValue());
				if (attachmentMetadata != null) {
					postmetaMapper.insert(attachmentMetadata);
				}

				// tagging
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

				// categorize
				List<EmbeddedCategory> embeddedCategories = embeddedCategoryMapper.selectByExample(new EmbeddedCategoryCriteria());
				for (EmbeddedCategory embeddedCategory : embeddedCategories) {
					if (StringUtils.contains(serviceModel.getPostTitle().toLowerCase(), embeddedCategory.getKeyword().toLowerCase())) {
						serviceModel.addCategorys(embeddedCategory.getCategory());
					}
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
	}
}
