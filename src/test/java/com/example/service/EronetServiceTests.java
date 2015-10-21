package com.example.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.TestBase;

public class EronetServiceTests extends TestBase {
	@Autowired
	ICrawlService eronetService;

	@Test
	public void crawl_test() {
		eronetService.doCrawl("http://xxeronetxx.info/ranking1day.html");
	}

}
