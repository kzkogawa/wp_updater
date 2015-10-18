package com.example;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ICrawlService;

public class CrawlServiceTests extends TestBase {
	@Autowired
	ICrawlService demoService;

	@Test
	public void crawl_test() {
		demoService.doCrawl();
	}

}
