package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.TestBase;
import com.example.model.PostServiceModel;

public class PostServiceTests extends TestBase {
	@Autowired
	PostService postService;

	@Test
	public void InsertPostsTest() {
		List<PostServiceModel> newPosts = new ArrayList<PostServiceModel>();
		postService.InsertPosts(newPosts);
	}

}
