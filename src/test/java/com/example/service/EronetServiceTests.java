package com.example.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.example.TestBase;
import com.example.service.crawl.ICrawlService;

public class EronetServiceTests extends TestBase {
	@Autowired
	@Qualifier("EronetService")
	private ICrawlService crawlService;

	@Test
	public void crawl_test() {
		crawlService.doCrawl("http://xxeronetxx.info/ranking1day.html");
	}

}
