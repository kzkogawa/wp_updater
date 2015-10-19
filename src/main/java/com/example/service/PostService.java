package com.example.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.factory.WpPostsWithBLOBFactory;
import com.example.mapper.WpPostmetaMapper;
import com.example.mapper.WpPostsMapper;
import com.example.mapper.WpTermRelationshipsMapper;
import com.example.model.wp.WpPostmeta;
import com.example.model.wp.WpPostsCriteria;
import com.example.model.wp.WpPostsWithBLOBs;

@Component
public class PostService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpPostsMapper postsMapper;
	@Autowired
	private WpPostmetaMapper postmetaMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;

	public void InsertPosts(List<WpPostsWithBLOBFactory> wpPostsWithBLOBFactories) {
		for (WpPostsWithBLOBFactory wpPostsWithBLOBFactory : wpPostsWithBLOBFactories) {
			WpPostsWithBLOBs post = wpPostsWithBLOBFactory.getPostForInsert();
			WpPostsCriteria wpPostsCriteria = new WpPostsCriteria();
			wpPostsCriteria.createCriteria().andPostNameLike(post.getPostName());
			if (postsMapper.selectByExample(wpPostsCriteria).size() == 0) {
				// insert post
				postsMapper.insert(post);
				wpPostsWithBLOBFactory.preparePostUpdate();
				postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getPostForUpdate());
				// insert revision
				WpPostsWithBLOBs revision = wpPostsWithBLOBFactory.getRevisionForInsert();
				postsMapper.insert(revision);
				wpPostsWithBLOBFactory.prepareRevisionUpdate();
				postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getRevisionForUpdate());
				// insert image
				WpPostsWithBLOBs image = wpPostsWithBLOBFactory.getImageForInsert();
				postsMapper.insert(image);
				wpPostsWithBLOBFactory.prepareImageUpdate();
				postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getImageForUpdate());
				// insert postmetas
				for (WpPostmeta postmeta : wpPostsWithBLOBFactory.getPostMetas()) {
					postmetaMapper.insert(postmeta);
				}
				// TODO:insert post-tag-relations
			} else {
				log.info("same post found " + post);
			}
		}
	}
}
