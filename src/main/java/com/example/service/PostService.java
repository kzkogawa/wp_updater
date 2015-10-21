package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.factory.WpPostmetaFactory;
import com.example.factory.WpPostsWithBLOBFactory;
import com.example.mapper.WpPostmetaMapper;
import com.example.mapper.WpPostsMapper;
import com.example.mapper.WpTermRelationshipsMapper;
import com.example.model.PostServiceModel;
import com.example.model.wp.WpPostmeta;
import com.example.model.wp.WpPostsCriteria;
import com.example.model.wp.WpPostsWithBLOBs;
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

	public void InsertPosts(List<PostServiceModel> serviceModels) {
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
				WpPostmeta attachedFileMeta = WpPostmetaFactory.getImageAttachedFileMetas(post.getId(), serviceModel);
				postmetaMapper.insert(attachedFileMeta);

				for (String tag : serviceModel.getTags()) {
					// TODO
				}
				for (String category : serviceModel.getCategorys()) {
					// TODO
				}
			} else {
				log.info("same post found " + post);
			}
		}
	}
}
