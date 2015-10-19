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

@Component
public class PostService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private WpPostsMapper postsMapper;
	@Autowired
	private WpPostmetaMapper postmetaMapper;
	@Autowired
	private WpTermRelationshipsMapper relationshipsMapper;

	public void InsertPosts(List<WpPostsWithBLOBFactory> newPosts) {
		for (WpPostsWithBLOBFactory wpPostsWithBLOBFactory : newPosts) {
			// insert post
			postsMapper.insert(wpPostsWithBLOBFactory.getPostForInsert());
			wpPostsWithBLOBFactory.preparePostUpdate();
			postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getPostForUpdate());
			// insert revision
			postsMapper.insert(wpPostsWithBLOBFactory.getRevisionForInsert());
			wpPostsWithBLOBFactory.prepareRevisionUpdate();
			postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getRevisionForUpdate());
			// insert revision
			postsMapper.insert(wpPostsWithBLOBFactory.getImageForInsert());
			wpPostsWithBLOBFactory.prepareImageUpdate();
			postsMapper.updateByPrimaryKeyWithBLOBs(wpPostsWithBLOBFactory.getImageForUpdate());
			// insert postmetas
			for (WpPostmeta postmeta : wpPostsWithBLOBFactory.getPostMetas()) {
				postmetaMapper.insert(postmeta);
			}

			// TODO:insert post-tag-relations
		}
	}
}
