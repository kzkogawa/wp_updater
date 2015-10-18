package com.example;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.ICrawlService;

public class DemoServiceTests extends TestBase {
	@Autowired
	ICrawlService demoService;

	@Test
	public void findAccount_1() {
		demoService.doCrawl();
	}

}
