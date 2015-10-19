package com.example.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.TestBase;
import com.example.factory.WpPostsWithBLOBFactory;
import com.example.model.EronetModel;
import com.example.service.EronetService;
import com.example.service.ICrawlService;

public class EronetServiceTests extends TestBase {
	@Autowired
	ICrawlService eronetService;

	@Test
	public void crawl_test() {
		eronetService.doCrawl("http://xxeronetxx.info/ranking1day.html");
	}

	@Test
	public void convertModel_test() {
		EronetService service = new EronetService();
		EronetModel dataModel = new EronetModel();
		dataModel.setImageAlt("alt");
		dataModel.setImageLink("http://link.com");
		dataModel.setImageSrc("http://src.com");
		dataModel.getVideoUrls().add("http://video1.com");
		dataModel.getVideoUrls().add("http://video2.com");
		WpPostsWithBLOBFactory postsWithBLOBFactory = service.convertModel(dataModel);
		Assert.assertEquals("alt", postsWithBLOBFactory.getPostForInsert().getPostTitle());
		Assert.assertEquals("alt", postsWithBLOBFactory.getRevisionForUpdate().getPostTitle());
	}
}
