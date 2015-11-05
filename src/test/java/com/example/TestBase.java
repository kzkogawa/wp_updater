package com.example;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WpUpdaterApplication.class)
//@Transactional
public class TestBase {

	@Before
	public void setup() {

	}

	// TODO customize db-unit
	// 1.delete all data when setup(make sure its gonna be roll backed)
}
