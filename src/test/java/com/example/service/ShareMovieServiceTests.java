package com.example.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.TestBase;
import com.example.service.crawl.ICrawlService;

public class ShareMovieServiceTests extends TestBase {
	@Autowired
	@Qualifier("ShareMovieService")
	private ICrawlService crawlService;

	@Test
	public void crawl_test() {
		crawlService.doCrawl("http://smv.to/list/ranking");
	}

}
