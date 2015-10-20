package com.example;

import org.junit.Assert;
import org.junit.Test;

import com.example.factory.WpPostsWithBLOBFactory;
import com.example.model.wp.WpPostsWithBLOBs;

public class WpPostsWithBLOBFactoryTests {
	@Test
	public void setImageLink_test() {
		WpPostsWithBLOBFactory factory = new WpPostsWithBLOBFactory();
		factory.setImageLink("http://xxeronetxx.info/img/20151017/ddac8ecfbe527f7b341847d5117cc76d1881fd2c.jpg");
		WpPostsWithBLOBs image = factory.getImageForInsert();

		Assert.assertNotNull(image.getGuid());
	}
}
